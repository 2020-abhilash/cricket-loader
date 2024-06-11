package com.sonabhi.cricket;

import com.sonabhi.cricket.model.MatchInfo;

public interface MatchInfoObserver {
    void update(MatchInfo matchInfo);
}
