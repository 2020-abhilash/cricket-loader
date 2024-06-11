package com.sonabhi.cricket.model;

import java.time.LocalDate;

public record BallByBallInfo(
    String matchId,
    String season,
    LocalDate startDate,
    String venue,
    int innings,
    String ball,
    String battingTeam,
    String bowlingTeam,
    String striker,
    String nonStriker,
    String bowler,
    int runsOffBat,
    int extras,
    int wides,
    int noballs,
    int byes,
    int legbyes,
    int penalty,
    String wicketType,
    String playerDismissed,
    String otherWicketType,
    String otherPlayerDismissed
) {
}
