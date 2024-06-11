package com.sonabhi.cricket.reader;

import com.sonabhi.cricket.MatchInfoSubject;
import com.sonabhi.cricket.model.MatchInfo;
import com.sonabhi.cricket.model.builder.MatchInfoBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchInfoFactory implements InfoFactory<MatchInfo> {
    // Logger instance
    private static final Logger logger = LogManager.getLogger(MatchInfoFactory.class);

    /* Singleton Implementation - Start */
    private MatchInfoFactory() {
    }

    public static MatchInfoFactory getInstance() {
        return Holder.INSTANCE;
    }
    /* Singleton Implementation - End */

    private static void logWarning(String line, String filename) {
        logger.warn("Ignoring line {} in file {}", line, filename);
    }

    private final MatchInfoSubject subject = new MatchInfoSubject();

    public MatchInfoSubject getSubject() {
        return subject;
    }

    @Override
    public void createInfo(Path path) {
        String filename = path.getFileName().toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()), 8192 * 10)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String matchId = filename.split("_")[0];
            String version = null;
            String ballsPerOver = null;
            String bowlOut = null;
            String city = null;
            String event = null;
            List<LocalDate> dates = new ArrayList<>();
            String eliminator = null;
            List<String> teams = new ArrayList<>();
            Map<String, List<String>> players = new HashMap<>();
            String gender = null;
            String season = null;
            int matchNumber = 0;
            List<String> matchReferees = new ArrayList<>();
            String method = null;
            String neutralVenue = null;
            String outcome = null;
            List<String> playerOfMatch = new ArrayList<>();
            Map<String, String> registry = new HashMap<>();
            List<String> reserveUmpire = new ArrayList<>();
            String venue = null;
            String tossDecision = null;
            String tossUncontested = null;
            String tossWinner = null;
            List<String> tvUmpires = new ArrayList<>();
            List<String> umpires = new ArrayList<>();
            String winner = null;
            int winnerInnings = 0;
            int winnerRuns = 0;
            int winnerWickets = 0;

            Map<String, String> dataRegistry = new HashMap<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                switch (data.length) {
                    case 2:
                        if (data[0].equals("version")) {
                            version = data[1];
                        } else {
                            logWarning(line, filename);
                        }
                        break;
                    case 3:
                        if (data[0].equals("info")) {
                            switch (data[1]) {
                                case "winner_innings":
                                    winnerInnings = Integer.parseInt(data[2]);
                                    break;
                                case "winner_wickets":
                                    winnerWickets = Integer.parseInt(data[2]);
                                    break;
                                case "toss_uncontested":
                                    tossUncontested = data[2];
                                    break;
                                case "outcome":
                                    outcome = data[2];
                                    break;
                                case "neutral_venue":
                                    neutralVenue = data[2];
                                    break;
                                case "method":
                                    method = data[2];
                                    break;
                                case "eliminator":
                                    eliminator = data[2];
                                    break;
                                case "bowl_out":
                                    bowlOut = data[2];
                                    break;
                                case "balls_per_over":
                                    ballsPerOver = data[2];
                                    break;
                                case "team":
                                    teams.add(data[2]);
                                    break;
                                case "gender":
                                    gender = data[2];
                                    break;
                                case "season":
                                    season = data[2];
                                    break;
                                case "date":
                                    dates.add(LocalDate.parse(data[2], formatter));
                                    break;
                                case "event":
                                    event = data[2];
                                    break;
                                case "match_number":
                                    matchNumber = Integer.parseInt(data[2]);
                                    break;
                                case "venue":
                                    venue = data[2];
                                    break;
                                case "city":
                                    city = data[2];
                                    break;
                                case "toss_winner":
                                    tossWinner = data[2];
                                    break;
                                case "toss_decision":
                                    tossDecision = data[2];
                                    break;
                                case "player_of_match":
                                    playerOfMatch.add(data[2]);
                                    break;
                                case "umpire":
                                    umpires.add(data[2]);
                                    break;
                                case "reserve_umpire":
                                    reserveUmpire.add(data[2]);
                                    break;
                                case "tv_umpire":
                                    tvUmpires.add(data[2]);
                                    break;
                                case "match_referee":
                                    matchReferees.add(data[2]);
                                    break;
                                case "winner":
                                    winner = data[2];
                                    break;
                                case "winner_runs":
                                    winnerRuns = Integer.parseInt(data[2]);
                                    break;
                                default:
                                    logWarning(line, filename);
                            }
                        }
                        break;
                    case 4:
                        if (data[1].equals("player") || data[1].equals("players")) {
                            if (players.containsKey(data[2])) {
                                players.get(data[2]).add(data[3]);
                            } else {
                                List<String> playerList = new ArrayList<>();
                                playerList.add(data[3]);
                                players.put(data[2], playerList);
                            }
                        } else {
                            logWarning(line, filename);
                        }
                        break;
                    case 5:
                        if (data[1].equals("registry") && data[2].equals("people")) {
                            if (registry.containsKey(data[4])) {
                                logger.warn("Duplicate entry for {} in file {}", data[4], filename);
                            } else {
                                registry.put(data[4], data[3]);
                            }
                        } else {
                            logWarning(line, filename);
                        }
                        break;
                    default:
                        logWarning(line, filename);
                }
            }

            MatchInfo matchInfo = new MatchInfoBuilder()
                    .matchId(matchId)
                    .version(version)
                    .ballsPerOver(ballsPerOver)
                    .bowlOut(bowlOut)
                    .city(city)
                    .event(event)
                    .dates(dates)
                    .eliminator(eliminator)
                    .gender(gender)
                    .matchNumber(matchNumber)
                    .matchReferees(matchReferees)
                    .method(method)
                    .neutralVenue(neutralVenue)
                    .outcome(outcome)
                    .playerOfMatch(playerOfMatch)
                    .players(players)
                    .registry(registry)
                    .reserveUmpire(reserveUmpire)
                    .season(season)
                    .teams(teams)
                    .tossDecision(tossDecision)
                    .tossUncontested(tossUncontested)
                    .tossWinner(tossWinner)
                    .tvUmpires(tvUmpires)
                    .umpires(umpires)
                    .venue(venue)
                    .winner(winner)
                    .winnerInnings(winnerInnings)
                    .winnerRuns(winnerRuns)
                    .winnerWickets(winnerWickets)
                    .build();

            subject.notifyObserver(matchInfo);

        } catch (IOException e) {
            logger.error("Error reading file {}", path, e);
            throw new InfoCreationException("Error reading file " + path, e);
        }
    }

    private static final class Holder {
        private static final MatchInfoFactory INSTANCE = new MatchInfoFactory();
    }
}
