package com.kodelabs.boilerplate.threading;

import com.kodelabs.boilerplate.domain.executor.MainThread;

public class TestMainThread implements MainThread {
    @Override
    public void post(Runnable runnable) {
        runnable.run();
    }
}
