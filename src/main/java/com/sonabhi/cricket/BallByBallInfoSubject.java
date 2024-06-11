package com.sonabhi.cricket;

import com.sonabhi.cricket.model.BallByBallInfo;

import java.util.List;

public class BallByBallInfoSubject {
    private BallByBallInfoObserver observer;

    public void registerObserver(BallByBallInfoObserver observer) {
        this.observer = observer;
    }

    public void notifyObserver(List<BallByBallInfo> ballByBallInfoList) {
        observer.update(ballByBallInfoList);
    }
}
