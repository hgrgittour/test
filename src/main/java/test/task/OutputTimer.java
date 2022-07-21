package test.task;

import test.util.CollectionDb;

import java.util.TimerTask;

public class OutputTimer extends TimerTask {
    @Override
    public void run() {
        CollectionDb.forEachMap();
    }
}
