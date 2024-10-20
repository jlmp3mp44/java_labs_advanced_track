import java.util.Random;

/**
 * Represents a participant in a survey with attributes such as city, age,
 * name, and monthly income. Implements the HasGField interface to provide
 * access to a specific field for statistical calculations.
 */
class SurveyParticipant implements HasGField {

  private String city;
  private int age;
  private String name;
  private double monthlyIncome;

  /**
   * Constructs a SurveyParticipant with the specified attributes.
   *
   * @param city          the city of the participant
   * @param age           the age of the participant
   * @param name          the name of the participant
   * @param monthlyIncome  the monthly income of the participant
   */
  public SurveyParticipant(String city, int age, String name, double monthlyIncome) {
    this.city = city;
    this.age = age;
    this.name = name;
    this.monthlyIncome = monthlyIncome;
  }

  /**
   * Returns the value of the monthly income, which is used as the G field.
   *
   * @return the monthly income of the participant
   */
  @Override
  public double getGField() {
    return this.monthlyIncome; // або інше поле, яке треба використовувати
  }

  /**
   * Returns the age of the participant.
   *
   * @return the age of the participant
   */
  public int getAge() {
    return age;
  }

  /**
   * Returns a string representation of the SurveyParticipant.
   *
   * @return a formatted string with the participant's details
   */
  @Override
  public String toString() {
    return "SurveyParticipant{" +
        "city='" + city + '\'' +
        ", age=" + age +
        ", name='" + name + '\'' +
        ", monthlyIncome=" + monthlyIncome +
        '}';
  }

  /**
   * Returns the city of the participant.
   *
   * @return the city of the participant
   */
  public String getCity() {
    return city;
  }

  /**
   * Generates a random SurveyParticipant with random attributes.
   *
   * @return a randomly generated SurveyParticipant
   */
  public static SurveyParticipant generateRandomPerson() {
    Random random = new Random();
    String[] names = {"John", "Anna", "Michael", "Olena", "Viktor"};
    String[] cities = {"Kyiv", "Lviv", "Odesa", "Dnipro"};

    String name = names[random.nextInt(names.length)];
    String city = cities[random.nextInt(cities.length)];
    int age = 18 + random.nextInt(50); // Вік від 18 до 68
    double income = 20000 + random.nextDouble() * 80000; // Дохід від 20k до 100k

    return new SurveyParticipant(city, age, name, income);
  }
}
