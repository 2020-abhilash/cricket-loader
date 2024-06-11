package com.sonabhi.cricket;

import com.sonabhi.cricket.database.dao.BallByBallInfoDao;
import com.sonabhi.cricket.database.dao.MatchInfoDao;
import com.sonabhi.cricket.reader.BallByBallInfoFactory;
import com.sonabhi.cricket.reader.InfoFactory;
import com.sonabhi.cricket.reader.InfoFactoryProducer;
import com.sonabhi.cricket.reader.MatchInfoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

public class App {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final Semaphore semaphore = new Semaphore(20);

    public static void main(String[] args) {
        String directoryPath = resourceBundle.getString("csvFileDirectoryPath");
        List<Thread> threads = new ArrayList<>();

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : directoryStream) {
                String filename = path.getFileName().toString();

                Thread thread = Thread.startVirtualThread(() -> {
                    try {
                        semaphore.acquire();
                        InfoFactory<?> infoFactory = InfoFactoryProducer.getFactory(filename);

                        if (infoFactory instanceof MatchInfoFactory matchInfoFactory) {
                            matchInfoFactory.getSubject().registerObserver(MatchInfoDao.getInstance());
                            matchInfoFactory.createInfo(path);
                        } else if (infoFactory instanceof BallByBallInfoFactory ballByBallInfoFactory) {
                            ballByBallInfoFactory.getSubject().registerObserver(BallByBallInfoDao.getInstance());
                            ballByBallInfoFactory.createInfo(path);
                        } else {
                            logger.warn("Ignoring file {}", path);
                        }
                    } catch (InterruptedException e) {
                        logger.error("Thread interrupted", e);
                    } finally {
                        semaphore.release();
                    }
                });

                threads.add(thread);
            }

            for (Thread thread : threads) {
                thread.join();
            }

            logger.info("Data insertion completed");
        } catch (IOException | InterruptedException e) {
            logger.error("Error reading directory {}", directoryPath, e);
        }
    }
}
