package com.sonabhi.cricket.reader;

public class InfoFactoryProducer {
    public static InfoFactory<?> getFactory(String filename) {
        if (filename.endsWith("_info.csv")) {
            return MatchInfoFactory.getInstance();
        } else if (filename.endsWith(".csv")) {
            return BallByBallInfoFactory.getInstance();
        } else {
            return null;
        }
    }
}
