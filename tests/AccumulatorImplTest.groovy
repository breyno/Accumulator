import spock.lang.Unroll

class AccumulatorImplTest extends spock.lang.Specification {
    Accumulator accumulator

    private static int[] EMPTY_ARRAY = []
    private static int[] ARRAY_ONE = [9]
    private static int[] ARRAY_TWO = [1, 2]
    private static int[] ARRAY_THREE = [5, 4, 3]

    private static int EMPTY_ARRAY_SUM = 0
    private static int ARRAY_ONE_SUM = 9
    private static int ARRAY_TWO_SUM = 3
    private static int ARRAY_THREE_SUM = 12
    private static int TOTAL_SUM = 24

    def setup() {
        accumulator = new AccumulatorImpl()
    }

    @Unroll
    def "Test accumulate(int...) correctly accumulates given values #values to #result"() {
        expect:
            accumulator.accumulate(values) == result
        where:
            values      |   result
            1           |   1
            EMPTY_ARRAY |   EMPTY_ARRAY_SUM
            ARRAY_ONE   |   ARRAY_ONE_SUM
            ARRAY_TWO   |   ARRAY_TWO_SUM
            ARRAY_THREE |   ARRAY_THREE_SUM
    }

    def "Test that multiple calls to accumulate(int...) increments the total"() {
        when:
            accumulator.accumulate(ARRAY_ONE_SUM)
            accumulator.accumulate(ARRAY_TWO_SUM)
            accumulator.accumulate(ARRAY_THREE_SUM)

        then:
            accumulator.getTotal() == TOTAL_SUM
    }

    def "Test that reset() successfully resets the accumulated total"() {
        when:
            accumulator.accumulate(ARRAY_ONE_SUM)
            accumulator.reset()

        then:
            accumulator.getTotal() == 0
    }

    def "Tests that accumulate(int...) updates the total after reset() is called"() {
        when:
        accumulator.accumulate(ARRAY_ONE_SUM)
        accumulator.reset()
        accumulator.accumulate(ARRAY_ONE_SUM)
        accumulator.accumulate(ARRAY_TWO_SUM)
        accumulator.accumulate(ARRAY_THREE_SUM)

        then:
        accumulator.getTotal() == TOTAL_SUM
    }
}
