import java.time.LocalTime;

public class TimeInterval<L extends LocalTime, R extends LocalTime> {
    private final L left;
    private final R right;

    TimeInterval(L left, R right) {
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
