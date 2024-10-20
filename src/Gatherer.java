import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A generic class that collects objects from a stream based on specific field values.
 *
 * @param <T> the type of objects to be gathered
 */
public class Gatherer<T> {

  /** The stream of objects to gather from. */
  private final Stream<T> generator;

  /** The value of the field used for filtering the objects. */
  private final String fieldValueToFilter;

  /** The number of objects to skip before gathering. */
  private final int skipN;

  /**
   * Constructs a new {@code Gatherer} with the specified parameters.
   *
   * @param generator the stream of objects to gather from
   * @param fieldValueToFilter the value of the field to filter objects
   * @param skipN the number of objects to skip
   */
  public Gatherer(Stream<T> generator, String fieldValueToFilter, int skipN) {
    this.generator = generator;
    this.fieldValueToFilter = fieldValueToFilter;
    this.skipN = skipN;
  }

  /**
   * Gathers a list of objects from the stream, filtering by the specified field value.
   *
   * @param limit the maximum number of objects to gather
   * @param extractor the function to extract the field value from the objects
   * @return a list of gathered objects
   */
  public List<T> gather(int limit, FieldExtractor<T> extractor) {
    return generator
        .filter(obj -> extractor.extractField(obj).equals(fieldValueToFilter)) // Фільтрація за полем A
        .skip(skipN) // Відкидаємо перші N об'єктів
        .limit(limit) // Обмежуємо список до 500
        .collect(Collectors.toList());
  }

  /**
   * A functional interface for extracting a field from an object.
   *
   * @param <T> the type of object to extract the field from
   */
  @FunctionalInterface
  public interface FieldExtractor<T> {

    /**
     * Extracts the field value from the given object.
     *
     * @param object the object to extract the field from
     * @return the extracted field value
     */
    String extractField(T object);
  }
}
