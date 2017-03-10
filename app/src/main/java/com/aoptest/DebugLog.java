package com.aoptest;

import android.util.Log;

/**
 * Created by Administrator on 2017/3/10.
 * Wrapper around {@link android.util.Log}
 */

public class DebugLog {
    private DebugLog(){}
    /**
     * send a debug log message
     *
     * @param tag Source of a log message
     * @param message The message you would like logged.
     */

    public static void log(String tag,String message){
        Log.d(tag,message);

    }
}
