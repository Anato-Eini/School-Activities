package com.example.javafx1.PerfectNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadRunnable implements Runnable{
    int start, end;

    final static List<Integer> numLTPNs = Collections.synchronizedList(new ArrayList<>()),
            numPNs = Collections.synchronizedList(new ArrayList<>()),
            numMTPNs = Collections.synchronizedList(new ArrayList<>());

    public ThreadRunnable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for(; start <= end; start++){
            int total = 0;
            for(int i = 1; i <= start / 2; i++)
                if (start % i == 0)
                    total += i;

            if(total < start)
                synchronized (numLTPNs){
                    numLTPNs.add(total);
                }
            else if(total == start)
                synchronized (numPNs){
                    numPNs.add(total);
                }
            else
                synchronized (numMTPNs){
                    numMTPNs.add(total);
                }
        }
    }
}
