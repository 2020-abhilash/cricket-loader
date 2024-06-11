package com.sonabhi.cricket.database.dao;

import com.sonabhi.cricket.MatchInfoObserver;
import com.sonabhi.cricket.database.DatabaseConnection;
import com.sonabhi.cricket.database.DatabaseConnectionPool;
import com.sonabhi.cricket.model.MatchInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MatchInfoDao implements MatchInfoObserver {

    /* Singleton class - Start */
    private MatchInfoDao() {}

    private static final class MatchInfoDaoHolder {
        private static final MatchInfoDao instance = new MatchInfoDao();
    }

    public static MatchInfoDao getInstance() {
        return MatchInfoDaoHolder.instance;
    }
    /* Singleton class - End */

    // Logger Instance
    private static final Logger logger = LogManager.getLogger(MatchInfoDao.class);

    @Override
    public void update(MatchInfo matchInfo) {
        try {
            insertMatchInfos(Collections.singletonList(matchInfo));
        } catch (SQLException e) {
            logger.error("Error inserting MatchInfo object: {}", matchInfo, e);
        }
    }

    private static final String INSERT_MATCHINFO_SQL = "INSERT INTO match_info" +
            " (match_id, version, balls_per_over, bowl_out, city, event, dates, eliminator, gender, match_number, " +
            "method, neutral_venue, outcome, season, toss_decision, toss_uncontested, toss_winner, venue, winner, " +
            "winner_innings, winner_runs, winner_wickets, match_referees, player_of_match, players, registry, " +
            "reserve_umpire, teams, tv_umpires, umpires) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private void insertMatchInfos(List<MatchInfo> matchInfos) throws SQLException {
        final int batchSize = 1000;

        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATCHINFO_SQL)) {
            int count = 0;

            for (MatchInfo matchInfo : matchInfos) {
                String matchInfoDatesPSV = String.join("|",
                        matchInfo.dates().stream().map(Object::toString).toList());
                String matchRefereesPSV = String.join("|",
                        matchInfo.matchReferees().stream().map(Object::toString).toList());
                String playerOfMatchPSV = String.join("|",
                        matchInfo.playerOfMatch().stream().map(Object::toString).toList());
                String playersPSV = String.join("|",
                        matchInfo.players().entrySet().stream()
                                .map(entry -> entry.getKey() + ":" + String.join("!", entry.getValue()))
                                .toList());
                String registryPSV = String.join("|",
                        matchInfo.registry().entrySet().stream()
                                .map(entry -> entry.getKey() + ":" + entry.getValue()).toList());
                String reserveUmpirePSV = String.join("|",
                        matchInfo.reserveUmpire().stream().map(Object::toString).toList());
                String teamsPSV = String.join("|", matchInfo.teams().stream().map(Object::toString).toList());
                String tvUmpiresPSV = String.join("|",
                        matchInfo.tvUmpires().stream().map(Object::toString).toList());
                String umpiresPSV = String.join("|",
                        matchInfo.umpires().stream().map(Object::toString).toList());

                preparedStatement.setString(1, matchInfo.matchId());
                preparedStatement.setString(2, matchInfo.version());
                preparedStatement.setString(3, matchInfo.ballsPerOver());
                preparedStatement.setString(4, matchInfo.bowlOut());
                preparedStatement.setString(5, matchInfo.city());
                preparedStatement.setString(6, matchInfo.event());
                preparedStatement.setString(7, matchInfoDatesPSV);
                preparedStatement.setString(8, matchInfo.eliminator());
                preparedStatement.setString(9, matchInfo.gender());
                preparedStatement.setInt(10, matchInfo.matchNumber());
                preparedStatement.setString(11, matchInfo.method());
                preparedStatement.setString(12, matchInfo.neutralVenue());
                preparedStatement.setString(13, matchInfo.outcome());
                preparedStatement.setString(14, matchInfo.season());
                preparedStatement.setString(15, matchInfo.tossDecision());
                preparedStatement.setString(16, matchInfo.tossUncontested());
                preparedStatement.setString(17, matchInfo.tossWinner());
                preparedStatement.setString(18, matchInfo.venue());
                preparedStatement.setString(19, matchInfo.winner());
                preparedStatement.setInt(20, matchInfo.winnerInnings());
                preparedStatement.setInt(21, matchInfo.winnerRuns());
                preparedStatement.setInt(22, matchInfo.winnerWickets());
                preparedStatement.setString(23, matchRefereesPSV);
                preparedStatement.setString(24, playerOfMatchPSV);
                preparedStatement.setString(25, playersPSV);
                preparedStatement.setString(26, registryPSV);
                preparedStatement.setString(27, reserveUmpirePSV);
                preparedStatement.setString(28, teamsPSV);
                preparedStatement.setString(29, tvUmpiresPSV);
                preparedStatement.setString(30, umpiresPSV);

                preparedStatement.addBatch();

                if (++count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
