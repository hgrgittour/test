package test.task;

import test.util.CollectionDb;

import java.util.Date;
import java.util.TimerTask;

public class OutputTimer extends TimerTask {
    @Override
    public void run() {
        System.out.println("start show currency at"+new Date());
        CollectionDb.forEachMap();
        System.out.println("end show currency at"+new Date());
    }
}
