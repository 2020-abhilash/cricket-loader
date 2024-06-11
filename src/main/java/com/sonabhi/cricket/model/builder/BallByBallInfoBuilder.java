package com.sonabhi.cricket.model.builder;

import com.sonabhi.cricket.model.BallByBallInfo;

import java.time.LocalDate;

public class BallByBallInfoBuilder {
    String matchId;
    String season;
    LocalDate startDate;
    String venue;
    int innings;
    String ball;
    String battingTeam;
    String bowlingTeam;
    String striker;
    String nonStriker;
    String bowler;
    int runsOffBat;
    int extras;
    int wides;
    int noballs;
    int byes;
    int legbyes;
    int penalty;
    String wicketType;
    String playerDismissed;
    String otherWicketType;
    String otherPlayerDismissed;

    public BallByBallInfoBuilder matchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    public BallByBallInfoBuilder season(String season) {
        this.season = season;
        return this;
    }

    public BallByBallInfoBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public BallByBallInfoBuilder venue(String venue) {
        this.venue = venue;
        return this;
    }

    public BallByBallInfoBuilder innings(int innings) {
        this.innings = innings;
        return this;
    }

    public BallByBallInfoBuilder ball(String ball) {
        this.ball = ball;
        return this;
    }

    public BallByBallInfoBuilder battingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
        return this;
    }

    public BallByBallInfoBuilder bowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
        return this;
    }

    public BallByBallInfoBuilder striker(String striker) {
        this.striker = striker;
        return this;
    }

    public BallByBallInfoBuilder nonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
        return this;
    }

    public BallByBallInfoBuilder bowler(String bowler) {
        this.bowler = bowler;
        return this;
    }

    public BallByBallInfoBuilder runsOffBat(int runsOffBat) {
        this.runsOffBat = runsOffBat;
        return this;
    }

    public BallByBallInfoBuilder extras(int extras) {
        this.extras = extras;
        return this;
    }

    public BallByBallInfoBuilder wides(int wides) {
        this.wides = wides;
        return this;
    }

    public BallByBallInfoBuilder noballs(int noballs) {
        this.noballs = noballs;
        return this;
    }

    public BallByBallInfoBuilder byes(int byes) {
        this.byes = byes;
        return this;
    }

    public BallByBallInfoBuilder legbyes(int legbyes) {
        this.legbyes = legbyes;
        return this;
    }

    public BallByBallInfoBuilder penalty(int penalty) {
        this.penalty = penalty;
        return this;
    }

    public BallByBallInfoBuilder wicketType(String wicketType) {
        this.wicketType = wicketType;
        return this;
    }

    public BallByBallInfoBuilder playerDismissed(String playerDismissed) {
        this.playerDismissed = playerDismissed;
        return this;
    }

    public BallByBallInfoBuilder otherWicketType(String otherWicketType) {
        this.otherWicketType = otherWicketType;
        return this;
    }

    public BallByBallInfoBuilder otherPlayerDismissed(String otherPlayerDismissed) {
        this.otherPlayerDismissed = otherPlayerDismissed;
        return this;
    }

    public BallByBallInfo build() {
        return new BallByBallInfo(
            matchId,
            season,
            startDate,
            venue,
            innings,
            ball,
            battingTeam,
            bowlingTeam,
            striker,
            nonStriker,
            bowler,
            runsOffBat,
            extras,
            wides,
            noballs,
            byes,
            legbyes,
            penalty,
            wicketType,
            playerDismissed,
            otherWicketType,
            otherPlayerDismissed
        );
    }
}
