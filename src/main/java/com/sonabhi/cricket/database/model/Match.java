package com.sonabhi.cricket.database.model;

import java.util.HashMap;
import java.util.List;

public record Match(
        String id,
        int ballsPerOver,
        City city,
        Event event,
        List<MatchDate> dates,
        Gender gender,
        int matchNumber,
        Boolean neutralVenue,
        Season season,
        Venue venue,
        List<Official> tvUmpires,
        List<Official> umpires,
        List<Official> matchReferees,
        List<Official> reserveUmpires,
        List<Player> playerOfMatch,
        List<Team> teams,
        HashMap<String, List<Player>> players
) {
}
