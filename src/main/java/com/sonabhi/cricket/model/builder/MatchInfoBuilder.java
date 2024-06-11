package com.sonabhi.cricket.model.builder;

import com.sonabhi.cricket.model.MatchInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MatchInfoBuilder {
    String matchId;
    String version;
    String ballsPerOver;
    String bowlOut;
    String city;
    String event;
    List<LocalDate> dates;
    String eliminator;
    String gender;
    int matchNumber;
    List<String> matchReferees;
    String method;
    String neutralVenue;
    String outcome;
    List<String> playerOfMatch;
    Map<String, List<String>> players;
    Map<String, String> registry;
    List<String> reserveUmpire;
    String season;
    List<String> teams;
    String tossDecision;
    String tossUncontested;
    String tossWinner;
    List<String> tvUmpires;
    List<String> umpires;
    String venue;
    String winner;
    int winnerInnings;
    int winnerRuns;
    int winnerWickets;

    public MatchInfoBuilder matchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    public MatchInfoBuilder version(String version) {
        this.version = version;
        return this;
    }

    public MatchInfoBuilder ballsPerOver(String ballsPerOver) {
        this.ballsPerOver = ballsPerOver;
        return this;
    }

    public MatchInfoBuilder bowlOut(String bowlOut) {
        this.bowlOut = bowlOut;
        return this;
    }

    public MatchInfoBuilder city(String city) {
        this.city = city;
        return this;
    }

    public MatchInfoBuilder event(String event) {
        this.event = event;
        return this;
    }

    public MatchInfoBuilder dates(List<LocalDate> dates) {
        this.dates = dates;
        return this;
    }

    public MatchInfoBuilder eliminator(String eliminator) {
        this.eliminator = eliminator;
        return this;
    }

    public MatchInfoBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public MatchInfoBuilder matchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
        return this;
    }

    public MatchInfoBuilder matchReferees(List<String> matchReferees) {
        this.matchReferees = matchReferees;
        return this;
    }

    public MatchInfoBuilder method(String method) {
        this.method = method;
        return this;
    }

    public MatchInfoBuilder neutralVenue(String neutralVenue) {
        this.neutralVenue = neutralVenue;
        return this;
    }

    public MatchInfoBuilder outcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    public MatchInfoBuilder playerOfMatch(List<String> playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
        return this;
    }

    public MatchInfoBuilder players(Map<String, List<String>> players) {
        this.players = players;
        return this;
    }

    public MatchInfoBuilder registry(Map<String, String> registry) {
        this.registry = registry;
        return this;
    }

    public MatchInfoBuilder reserveUmpire(List<String> reserveUmpire) {
        this.reserveUmpire = reserveUmpire;
        return this;
    }

    public MatchInfoBuilder season(String season) {
        this.season = season;
        return this;
    }

    public MatchInfoBuilder teams(List<String> teams) {
        this.teams = teams;
        return this;
    }

    public MatchInfoBuilder tossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
        return this;
    }

    public MatchInfoBuilder tossUncontested(String tossUncontested) {
        this.tossUncontested = tossUncontested;
        return this;
    }

    public MatchInfoBuilder tossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
        return this;
    }

    public MatchInfoBuilder tvUmpires(List<String> tvUmpires) {
        this.tvUmpires = tvUmpires;
        return this;
    }

    public MatchInfoBuilder umpires(List<String> umpires) {
        this.umpires = umpires;
        return this;
    }

    public MatchInfoBuilder venue(String venue) {
        this.venue = venue;
        return this;
    }

    public MatchInfoBuilder winner(String winner) {
        this.winner = winner;
        return this;
    }

    public MatchInfoBuilder winnerInnings(int winnerInnings) {
        this.winnerInnings = winnerInnings;
        return this;
    }

    public MatchInfoBuilder winnerRuns(int winnerRuns) {
        this.winnerRuns = winnerRuns;
        return this;
    }

    public MatchInfoBuilder winnerWickets(int winnerWickets) {
        this.winnerWickets = winnerWickets;
        return this;
    }

    public MatchInfo build() {
        return new MatchInfo(
                matchId,
                version,
                ballsPerOver,
                bowlOut,
                city,
                event,
                dates,
                eliminator,
                gender,
                matchNumber,
                matchReferees,
                method,
                neutralVenue,
                outcome,
                playerOfMatch,
                players,
                registry,
                reserveUmpire,
                season,
                teams,
                tossDecision,
                tossUncontested,
                tossWinner,
                tvUmpires,
                umpires,
                venue,
                winner,
                winnerInnings,
                winnerRuns,
                winnerWickets
        );
    }
}
