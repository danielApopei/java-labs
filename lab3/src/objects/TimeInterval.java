package objects;

import java.time.LocalTime;

/**
 * generic class that represents a TimeInterval
 * @param <L> the start of the interval
 * @param <R> the end of the interval
 */
public class TimeInterval<L extends LocalTime, R extends LocalTime> {
    private final L left;
    private final R right;

    public TimeInterval(L left, R right) {
        this.left = left;
        this.right = right;
    }
    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public String toString() {
        return "(" + left + " - " + right + ")";
    }
}
