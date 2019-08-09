package com.star.util;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {

    public static void schedule(Runnable runnable , long delay, long period) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               runnable.run();
            }
        };
        timer.schedule(timerTask, delay, period);
    }
}
