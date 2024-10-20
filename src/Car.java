import java.util.Random;

/**
 * Represents a car with its brand, production age, class, and price.
 * Implements the {@link HasGField} interface to provide a method
 * for retrieving a value associated with the car.
 */
class Car implements HasGField {

  /** The brand of the car. */
  String brand;

  /** The number of months since the car was produced. */
  int monthsSinceProduction;

  /** The class of the car (e.g., A, B, C, D, E). */
  String carClass;

  /** The price of the car. */
  double price;

  /**
   * Constructs a new {@code Car} with the specified brand, months since production,
   * class, and price.
   *
   * @param brand the brand of the car
   * @param monthsSinceProduction the number of months since the car was produced
   * @param carClass the class of the car
   * @param price the price of the car
   */
  public Car(String brand, int monthsSinceProduction, String carClass, double price) {
    this.brand = brand;
    this.monthsSinceProduction = monthsSinceProduction;
    this.carClass = carClass;
    this.price = price;
  }

  /**
   * Returns the price of the car, which is used as the G-field value.
   *
   * @return the price of the car
   */
  @Override
  public double getGField() {
    return this.price; // або інше поле, яке треба використовувати
  }

  /**
   * Returns a string representation of the car, including its brand,
   * months since production, class, and price.
   *
   * @return a string representation of the car
   */
  @Override
  public String toString() {
    return "Car{" +
        "brand='" + brand + '\'' +
        ", monthsSinceProduction=" + monthsSinceProduction +
        ", carClass='" + carClass + '\'' +
        ", price=" + price +
        '}';
  }

  /**
   * Returns the brand of the car.
   *
   * @return the brand of the car
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Returns the number of months since the car was produced.
   *
   * @return the number of months since production
   */
  public int getMonthsSinceProduction() {
    return monthsSinceProduction;
  }

  /**
   * Generates a random car with random brand, age, category, and price.
   *
   * @return a randomly generated {@code Car} object
   */
  public static Car generateRandomCar() {
    Random random = new Random();
    String[] brands = {"Toyota", "Honda", "Ford", "BMW", "Audi"};
    String[] categories = {"A", "B", "C", "D", "E"};

    String brand = brands[random.nextInt(brands.length)];
    int age = random.nextInt(15); // Вік до 15 років
    String category = categories[random.nextInt(categories.length)];
    double price = 10000 + random.nextDouble() * 40000; // Ціна від 10k до 50k

    return new Car(brand, age, category, price);
  }
}


