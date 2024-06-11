package com.sonabhi.cricket.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record MatchInfo(
    String matchId,
    String version,
    String ballsPerOver,
    String bowlOut,
    String city,
    String event,
    List<LocalDate> dates,
    String eliminator,
    String gender,
    int matchNumber,
    List<String> matchReferees,
    String method,
    String neutralVenue,
    String outcome,
    List<String> playerOfMatch,
    Map<String, List<String>> players,
    Map<String, String> registry,
    List<String> reserveUmpire,
    String season,
    List<String> teams,
    String tossDecision,
    String tossUncontested,
    String tossWinner,
    List<String> tvUmpires,
    List<String> umpires,
    String venue,
    String winner,
    int winnerInnings,
    int winnerRuns,
    int winnerWickets
) implements Info {
}
