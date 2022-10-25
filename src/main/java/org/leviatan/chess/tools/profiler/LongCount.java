package org.leviatan.chess.tools.profiler;

/**
 * LongCount.
 *
 * @author Alejandro
 */
public class LongCount {

    private long count = 1;

    /** Adds one. */
    public void addOne() {
        count++;
    }

    public long getCount() {
        return count;
    }
}
