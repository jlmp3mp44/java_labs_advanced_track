import java.util.Random;

/**
 * Represents a clothing item with its city of production, age, fabric type, and price.
 * Implements the {@link HasGField} interface to provide a method
 * for retrieving a value associated with the clothing item.
 */
class ClothingItem implements HasGField {

  /** The city where the clothing item was produced. */
  String city;

  /** The number of months since the clothing item was produced. */
  int monthsSinceProduction;

  /** The type of fabric used in the clothing item. */
  String fabricType;

  /** The price of the clothing item. */
  double price;

  /**
   * Constructs a new {@code ClothingItem} with the specified city, months since production,
   * fabric type, and price.
   *
   * @param city the city where the clothing item was produced
   * @param monthsSinceProduction the number of months since the clothing item was produced
   * @param fabricType the type of fabric used in the clothing item
   * @param price the price of the clothing item
   */
  public ClothingItem(String city, int monthsSinceProduction, String fabricType, double price) {
    this.city = city;
    this.monthsSinceProduction = monthsSinceProduction;
    this.fabricType = fabricType;
    this.price = price;
  }

  /**
   * Returns the price of the clothing item, which is used as the G-field value.
   *
   * @return the price of the clothing item
   */
  @Override
  public double getGField() {
    return this.price; // або інше поле, яке треба використовувати
  }

  /**
   * Returns a string representation of the clothing item, including its city,
   * months since production, fabric type, and price.
   *
   * @return a string representation of the clothing item
   */
  @Override
  public String toString() {
    return "ClothingItem{" +
        "city='" + city + '\'' +
        ", monthsSinceProduction=" + monthsSinceProduction +
        ", fabricType='" + fabricType + '\'' +
        ", price=" + price +
        '}';
  }

  /**
   * Returns the city where the clothing item was produced.
   *
   * @return the city of production
   */
  public String getCity() {
    return city;
  }

  /**
   * Returns the number of months since the clothing item was produced.
   *
   * @return the number of months since production
   */
  public int getMonthsSinceProduction() {
    return monthsSinceProduction;
  }

  /**
   * Generates a random clothing item with random city, age, fabric type, and price.
   *
   * @return a randomly generated {@code ClothingItem} object
   */
  public static ClothingItem generateRandomClothingItem() {
    Random random = new Random();
    String[] cities = {"Kyiv", "Lviv", "Odesa", "Kharkiv"};
    String[] fabrics = {"Cotton", "Wool", "Silk", "Polyester"};

    String city = cities[random.nextInt(cities.length)];
    int monthsSinceProduced = random.nextInt(24); // до 2 років
    String fabricType = fabrics[random.nextInt(fabrics.length)];
    double price = 50 + (random.nextDouble() * 150); // Випадкова ціна між 50 та 200

    return new ClothingItem(city, monthsSinceProduced, fabricType, price);
  }
}
