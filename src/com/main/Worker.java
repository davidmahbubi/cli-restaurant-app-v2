package com.main;

import com.main.listeners.CookingCompleteListener;
import java.util.ArrayList;

public class Worker {

    private final double timeRatio = 0.2;
    int workerID;
    String workerEmoticon;
    CookingCompleteListener completeListener;
    ArrayList<Integer> tableIndexes;
    Menu menu;
    long startTime;

    public Worker(int workerID, String workerEmoticon, CookingCompleteListener completeListener) {
        this.workerEmoticon = workerEmoticon;
        this.workerID = workerID;
        this.completeListener = completeListener;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setTableIndexes(ArrayList<Integer> tableIndexes) {
        this.tableIndexes = tableIndexes;
    }

    public Worker doCook() {
        new Thread(() -> {
            try {
                this.startTime = System.currentTimeMillis();

                System.out.printf("[%s Worker %d] : START cooking menu %s. Estimation Cooking Time %d sec (with %f ratio)\n", this.workerEmoticon, this.workerID, this.menu.name, this.menu.getCookingTimeInSecond(), this.timeRatio);
                Thread.sleep((long) menu.cookingTimeSec * (long) (1000 * timeRatio));

                System.out.printf("[%s Worker %d] : ✅ DONE  (real time execution took %d secs) for menu %s\n", this.workerEmoticon, workerID, (System.currentTimeMillis() - this.startTime) / 1000, menu.name);

                this.tableIndexes.forEach(val -> {
                    System.out.printf("\t- Removed menu %s from table number %d\n", menu.name, val);
                });

                completeListener.traceMenu(this.workerID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return this;
    }
}
