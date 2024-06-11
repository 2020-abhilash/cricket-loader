package com.sonabhi.cricket.reader;

import java.nio.file.Path;

public interface InfoFactory<T> {
    void createInfo(Path path);
}
