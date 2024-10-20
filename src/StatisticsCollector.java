import java.util.DoubleSummaryStatistics;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Provides a custom collector for collecting statistical data from a stream of Double values.
 */
public class StatisticsCollector {

  /**
   * Returns a Collector that accumulates statistics such as minimum, maximum,
   * average, and standard deviation from a stream of Double values.
   *
   * @return a custom collector for statistical data
   */
  public static Collector<Double, ?, StatisticsData> getStatisticsCollector() {
    return Collector.of(
        StatisticsData::new,              // Supplier
        StatisticsData::accept,           // Accumulator
        StatisticsData::combine,          // Combiner
        Collector.Characteristics.IDENTITY_FINISH // Characteristics
    );
  }

  /**
   * Stores statistical data including summary statistics and the sum of squares
   * for calculating the standard deviation.
   */
  public static class StatisticsData {

    private DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
    private double sumOfSquares = 0.0;

    /**
     * Accepts a new value for accumulation of statistics.
     *
     * @param value the value to be added to the statistics
     */
    public void accept(Double value) {
      statistics.accept(value);
      sumOfSquares += value * value;
    }

    /**
     * Combines this StatisticsData instance with another StatisticsData instance.
     *
     * @param other the other StatisticsData to combine with
     * @return the combined StatisticsData instance
     */
    public StatisticsData combine(StatisticsData other) {
      statistics.combine(other.statistics);
      sumOfSquares += other.sumOfSquares;
      return this;
    }

    /**
     * Calculates the standard deviation based on the collected data.
     *
     * @return the standard deviation of the collected values
     */
    public double getStandardDeviation() {
      double avg = statistics.getAverage();
      return Math.sqrt((sumOfSquares / statistics.getCount()) - (avg * avg));
    }

    /**
     * Returns a string representation of the statistical data including
     * minimum, maximum, average, and standard deviation.
     *
     * @return a formatted string of statistical results
     */
    @Override
    public String toString() {
      return String.format("Min: %.2f, Max: %.2f, Avg: %.2f, Std Dev: %.2f",
          statistics.getMin(),
          statistics.getMax(),
          statistics.getAverage(),
          getStandardDeviation());
    }
  }
}
