package com.example.myapplication3.model.databases;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseExecutor {
    private static final Executor executor = Executors.newSingleThreadExecutor();

    public static Executor getExecutor() {
        return executor;
    }
}
