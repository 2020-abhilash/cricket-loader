package com.sonabhi.cricket;

import com.sonabhi.cricket.model.BallByBallInfo;

import java.util.List;

public interface BallByBallInfoObserver {
    void update(List<BallByBallInfo> ballByBallInfoList);
}
