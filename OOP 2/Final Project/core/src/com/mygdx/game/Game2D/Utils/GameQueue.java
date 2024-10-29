package com.mygdx.game.Game2D.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class GameQueue {

    private static final Deque<Runnable> queue = new ArrayDeque<>();

    public static synchronized boolean isEmpty(){
        synchronized (queue){
            return queue.isEmpty();
        }
    }

    public static void add(Runnable runnable){
        synchronized (queue){
            queue.addLast(runnable);
        }
    }

    public static void run(){
        synchronized (queue){
            ArrayList<Thread> threads = new ArrayList<>();
            queue.forEach(runnable -> threads.add(new Thread(runnable)));
            threads.forEach(Thread::start);
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            queue.clear();
        }
    }
}
