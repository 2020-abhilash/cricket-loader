package com.sonabhi.cricket.reader;

import com.sonabhi.cricket.BallByBallInfoSubject;
import com.sonabhi.cricket.model.BallByBallInfo;
import com.sonabhi.cricket.model.builder.BallByBallInfoBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BallByBallInfoFactory implements InfoFactory<BallByBallInfo> {
    private static final Logger logger = LogManager.getLogger(BallByBallInfoFactory.class);
    private BallByBallInfoSubject subject = new BallByBallInfoSubject();

    /* Singleton Implementation - Start */
    private BallByBallInfoFactory() {
    }
    /* Singleton Implementation - End */

    public static BallByBallInfoFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static void logWarning(String line, String filename) {
        logger.warn("Ignoring line {} in file {}", line, filename);
    }

    public BallByBallInfoSubject getSubject() {
        if (subject == null) {
            subject = new BallByBallInfoSubject();
        }
        return subject;
    }

    @Override
    public void createInfo(Path path) {
        String filename = path.getFileName().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()), 8192 * 10)) {
            String line;
            List<BallByBallInfo> ballByBallInfoList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("match_id,")) {
                    continue;
                }

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (data.length != 22) {
                    logWarning(line, filename);
                    continue;
                }

                BallByBallInfo ballByBallInfo = new BallByBallInfoBuilder()
                        .matchId(data[0])
                        .season(data[1])
                        .startDate(LocalDate.parse(data[2], formatter))
                        .venue(data[3])
                        .innings(getIntOrThrow(data[4]))
                        .ball(data[5])
                        .battingTeam(data[6])
                        .bowlingTeam(data[7])
                        .striker(data[8])
                        .nonStriker(data[9])
                        .bowler(data[10])
                        .runsOffBat(getIntOrThrow(data[11]))
                        .extras(getIntOrThrow(data[12]))
                        .wides(getIntOrThrow(data[13]))
                        .noballs(getIntOrThrow(data[14]))
                        .byes(getIntOrThrow(data[15]))
                        .legbyes(getIntOrThrow(data[16]))
                        .penalty(getIntOrThrow(data[17]))
                        .wicketType(data[18])
                        .playerDismissed(data[19])
                        .otherWicketType(data[20])
                        .otherPlayerDismissed(data[21])
                        .build();

                ballByBallInfoList.add(ballByBallInfo);
            }

            subject.notifyObserver(ballByBallInfoList);
        } catch (IOException e) {
            logger.error("Error reading file {}", path, e);
            throw new InfoCreationException("Error reading file " + path, e);
        }
    }

    private int getIntOrThrow(String value) {
        return Objects.equals(value, "") ? 0 : Integer.parseInt(value);
    }

    private static final class Holder {
        private static final BallByBallInfoFactory INSTANCE = new BallByBallInfoFactory();
    }
}
