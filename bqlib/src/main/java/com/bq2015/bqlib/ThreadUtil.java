package com.bq2015.bqlib;

import android.os.Handler;

/**
 * @author bq2015
 */
public class ThreadUtil {
    private static Handler sHandler = new Handler();

    private ThreadUtil() {
    }

    /**
     * 在子线程执行任务
     *
     * @param task
     */
    public static void runInThread(Runnable task) {
        new Thread(task).start();
    }

    /**
     * 在UI线程执行任务
     *
     * @param task
     */
    public static void runInUIThread(Runnable task) {
        sHandler.post(task);
    }

    /**
     * 在UI线程延时执行任务
     *
     * @param task
     * @param delayMillis 延时时间，单位毫秒
     */
    public static void runInUIThread(Runnable task, long delayMillis) {
        sHandler.postDelayed(task, delayMillis);
    }

    /**
     * 移除指定任务
     * @param task 任务
     */
    public static void removeTask(Runnable task) {
        sHandler.removeCallbacks(task);
    }
}