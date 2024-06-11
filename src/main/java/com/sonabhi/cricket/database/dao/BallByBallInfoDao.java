package com.sonabhi.cricket.database.dao;

import com.sonabhi.cricket.BallByBallInfoObserver;
import com.sonabhi.cricket.database.DatabaseConnection;
import com.sonabhi.cricket.database.DatabaseConnectionPool;
import com.sonabhi.cricket.model.BallByBallInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BallByBallInfoDao implements BallByBallInfoObserver {

    private BallByBallInfoDao() {}

    private static final class BallByBallInfoDaoHolder {
        private static final BallByBallInfoDao instance = new BallByBallInfoDao();
    }

    public static BallByBallInfoDao getInstance() {
        return BallByBallInfoDaoHolder.instance;
    }

    // Logger instance
    private static final Logger logger = LogManager.getLogger(BallByBallInfoDao.class);

    @Override
    public void update(List<BallByBallInfo> ballByBallInfoList) {
        try {
            insertBallByBallInfos(ballByBallInfoList);
        } catch (SQLException e) {
            logger.error("Error inserting ball by ball info", e);
        }
    }

    private static final String INSERT_BALL_BY_BALL_INFO_SQL = "INSERT INTO ball_by_ball_info" +
            " (match_id, season, start_date, venue, innings, ball, batting_team, bowling_team, striker, non_striker, " +
            "bowler, runs_off_bat, extras, wides, noballs, byes, legbyes, penalty, wicket_type, player_dismissed, " +
            "other_wicket_type, other_player_dismissed) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private void insertBallByBallInfos(List<BallByBallInfo> ballByBallInfos) throws SQLException {
        final int batchSize = 1000;

        try (Connection connection = DatabaseConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BALL_BY_BALL_INFO_SQL)) {
            int count = 0;

            for (BallByBallInfo ballByBallInfo : ballByBallInfos) {
                preparedStatement.setString(1, ballByBallInfo.matchId());
                preparedStatement.setString(2, ballByBallInfo.season());
                preparedStatement.setDate(3, java.sql.Date.valueOf(ballByBallInfo.startDate()));
                preparedStatement.setString(4, ballByBallInfo.venue());
                preparedStatement.setInt(5, ballByBallInfo.innings());
                preparedStatement.setString(6, ballByBallInfo.ball());
                preparedStatement.setString(7, ballByBallInfo.battingTeam());
                preparedStatement.setString(8, ballByBallInfo.bowlingTeam());
                preparedStatement.setString(9, ballByBallInfo.striker());
                preparedStatement.setString(10, ballByBallInfo.nonStriker());
                preparedStatement.setString(11, ballByBallInfo.bowler());
                preparedStatement.setInt(12, ballByBallInfo.runsOffBat());
                preparedStatement.setInt(13, ballByBallInfo.extras());
                preparedStatement.setInt(14, ballByBallInfo.wides());
                preparedStatement.setInt(15, ballByBallInfo.noballs());
                preparedStatement.setInt(16, ballByBallInfo.byes());
                preparedStatement.setInt(17, ballByBallInfo.legbyes());
                preparedStatement.setInt(18, ballByBallInfo.penalty());
                preparedStatement.setString(19, ballByBallInfo.wicketType());
                preparedStatement.setString(20, ballByBallInfo.playerDismissed());
                preparedStatement.setString(21, ballByBallInfo.otherWicketType());
                preparedStatement.setString(22, ballByBallInfo.otherPlayerDismissed());
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
