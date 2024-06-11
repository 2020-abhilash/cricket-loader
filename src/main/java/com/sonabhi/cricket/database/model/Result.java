package com.sonabhi.cricket.database.model;

public record Result(
    Team tossWinner,
    Team winner,
    Team bowlOutWinner,
    Team eliminationWinner,
    Method winnerDeterminationMethod,
    Outcome outcome,
    TossDecision tossDecision,
    Boolean tossUncontested,
    int winnerInnings,
    int winnerRuns,
    int winnerWickets
) {
}
