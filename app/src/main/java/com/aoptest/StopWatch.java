package com.aoptest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/10.
 * 一个秒表测量时间类
 */

public class StopWatch {
    private long statTime;
    private long endTime;
    private long elapsedTime;

    public StopWatch() {

    }

    private void reset(){
        statTime=0;
        endTime=0;
        elapsedTime=0;
    }
    public void start(){
        reset();
        statTime=System.nanoTime();
    }
    public void stop(){
        if (statTime!=0) {
            endTime=System.nanoTime();
            elapsedTime=endTime-statTime;
        }else {
            reset();
        }
    }

    public long getToatalTimeMillis(){
        return (elapsedTime!=0)? TimeUnit.NANOSECONDS.toMillis(endTime-statTime):0;

    }
}
