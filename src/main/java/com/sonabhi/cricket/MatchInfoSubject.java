package com.sonabhi.cricket;

import com.sonabhi.cricket.model.MatchInfo;

public class MatchInfoSubject {
    private MatchInfoObserver observer;

    public void registerObserver(MatchInfoObserver observer) {
        this.observer = observer;
    }

    public void notifyObserver(MatchInfo matchInfo) {
        observer.update(matchInfo);
    }
}
