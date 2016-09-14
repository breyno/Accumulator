import java.util.Arrays;

public class AccumulatorImpl implements Accumulator {
    private int total = 0;

    @Override
    public int accumulate(int... values) {
        int sum = Arrays.stream(values).sum();
        total+=sum;
        return sum;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public void reset() {
        total = 0;
    }
}
