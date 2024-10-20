import java.util.Random;

/**
 * Represents a mythical creature with attributes such as type, age, and attack power.
 * This class implements the HasGField interface to provide a method for retrieving the attack power.
 */
class MythicalCreature implements HasGField {

  private String type; // The type of the mythical creature
  private int yearsSinceFirstAppearance; // The number of years since the creature first appeared
  private double attackPower; // The attack power of the creature

  /**
   * Constructs a MythicalCreature with specified attributes.
   *
   * @param type                     the type of the creature
   * @param yearsSinceFirstAppearance the number of years since the creature's first appearance
   * @param attackPower              the attack power of the creature
   */
  public MythicalCreature(String type, int yearsSinceFirstAppearance, double attackPower) {
    this.type = type;
    this.yearsSinceFirstAppearance = yearsSinceFirstAppearance;
    this.attackPower = attackPower;
  }

  /**
   * Returns the attack power of the creature, used for G-field calculations.
   *
   * @return the attack power of the creature
   */
  @Override
  public double getGField() {
    return this.attackPower; // or another field to use
  }

  /**
   * Returns a string representation of the MythicalCreature object.
   *
   * @return a string representation of the MythicalCreature
   */
  @Override
  public String toString() {
    return "MythicalCreature{" +
        "type='" + type + '\'' +
        ", yearsSinceFirstAppearance=" + yearsSinceFirstAppearance +
        ", attackPower=" + attackPower +
        '}';
  }

  /**
   * Returns the type of the mythical creature.
   *
   * @return the type of the creature
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the number of years since the mythical creature's first appearance.
   *
   * @return the years since first appearance
   */
  public int getYearsSinceFirstAppearance() {
    return yearsSinceFirstAppearance;
  }

  /**
   * Generates a random mythical creature with random attributes.
   *
   * @return a randomly generated MythicalCreature
   */
  public static MythicalCreature generateRandomCreature() {
    Random random = new Random();
    String[] speciesArray = {"Dragon", "Unicorn", "Phoenix", "Goblin", "Griffin"};
    String[] rarities = {"Common", "Rare", "Legendary"};

    String species = speciesArray[random.nextInt(speciesArray.length)];
    int age = random.nextInt(1000); // Age up to 1000 years
    String rarity = rarities[random.nextInt(rarities.length)];
    double strength = random.nextDouble() * 10000; // Strength up to 10k

    return new MythicalCreature(species, age, strength);
  }
}
