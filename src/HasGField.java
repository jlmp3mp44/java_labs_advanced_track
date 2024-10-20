/**
 * An interface that represents an object with a G-field value.
 * Implementing classes must provide a way to retrieve the G-field value.
 */
public interface HasGField {

  /**
   * Returns the G-field value associated with the object.
   *
   * @return the G-field value as a double
   */
  double getGField();
}
