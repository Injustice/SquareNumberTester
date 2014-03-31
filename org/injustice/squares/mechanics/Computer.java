package org.injustice.squares.mechanics;

import java.util.HashMap;

/**
 * Created by Azmat on 28/03/2014.
 */
public class Computer {
    private final HashMap<Integer, Long> timeTaken;

    public Computer(HashMap<Integer, Long> timeTaken) {
        this.timeTaken = timeTaken;
    }

    private int computeTotalTime() {
        int totalTime = 0;
        for (Integer i : timeTaken.keySet()) {
            totalTime += timeTaken.get(i);
        }
        return totalTime;
    }

    public double computeAverage() {
        return computeTotalTime() / timeTaken.size();
    }

    public double computeHighest() {
        long highest = Integer.MIN_VALUE;
        for (Integer i : timeTaken.keySet()) {
            if (highest < timeTaken.get(i)) {
                highest = timeTaken.get(i);
            }
        }
        return highest;
    }

    public double computeLowest() {
        long lowest = Integer.MAX_VALUE;
        for (Integer i : timeTaken.keySet()) {
            if (lowest > timeTaken.get(i)) {
                lowest = timeTaken.get(i);
            }
        }
        return lowest;
    }
}
