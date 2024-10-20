import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Main class that generates and filters various types of objects based on user input.
 * It can generate and filter survey participants, clothing items, cars, or mythical creatures.
 */
public class Main {

  /**
   * The main method that serves as the entry point for the program.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введіть номер залікової книжки: ");
    int studentID = scanner.nextInt();

    int C4 = studentID % 4;
    System.out.println("C4: " + C4);

    System.out.print("Введіть кількість об'єктів, які потрібно пропустити (N): ");
    int N = scanner.nextInt();

    System.out.print("Введіть значення для фільтрації за полем A: ");
    scanner.nextLine();  // очищення буфера
    String fieldValueToFilter = scanner.nextLine();

    System.out.print("Введіть мінімальне значення для фільтрації за параметром Б: ");
    double minB = scanner.nextDouble();

    System.out.print("Введіть максимальне значення для фільтрації за параметром Б: ");
    double maxB = scanner.nextDouble();

    List<?> resultList;

    switch (C4) {
      case 0:
        System.out.println("\nГенерація і фільтрація учасників опитування:");
        Stream<SurveyParticipant> participantStream = Stream.generate(SurveyParticipant::generateRandomPerson);
        Gatherer<SurveyParticipant> participantGatherer = new Gatherer<>(participantStream, fieldValueToFilter, N);
        List<SurveyParticipant> participants = participantGatherer.gather(500, SurveyParticipant::getCity);
        participants = filterByParameterB(participants, SurveyParticipant::getAge, minB, maxB);
        groupAndPrintResults(participants, SurveyParticipant::getCity);
        resultList = participants;
        break;
      case 1:
        System.out.println("\nГенерація і фільтрація одягу:");
        Stream<ClothingItem> clothingStream = Stream.generate(ClothingItem::generateRandomClothingItem);
        Gatherer<ClothingItem> clothingGatherer = new Gatherer<>(clothingStream, fieldValueToFilter, N);
        List<ClothingItem> clothingItems = clothingGatherer.gather(500, ClothingItem::getCity);
        clothingItems = filterByParameterB(clothingItems, ClothingItem::getMonthsSinceProduction, minB, maxB);
        groupAndPrintResults(clothingItems, ClothingItem::getCity);
        resultList = clothingItems;
        break;
      case 2:
        System.out.println("\nГенерація і фільтрація автомобілів:");
        Stream<Car> carStream = Stream.generate(Car::generateRandomCar);
        Gatherer<Car> carGatherer = new Gatherer<>(carStream, fieldValueToFilter, N);
        List<Car> cars = carGatherer.gather(500, Car::getBrand);
        cars = filterByParameterB(cars, Car::getMonthsSinceProduction, minB, maxB);
        groupAndPrintResults(cars, Car::getBrand);
        resultList = cars;
        break;
      case 3:
        System.out.println("\nГенерація і фільтрація міфічних істот:");
        Stream<MythicalCreature> creatureStream = Stream.generate(MythicalCreature::generateRandomCreature);
        Gatherer<MythicalCreature> creatureGatherer = new Gatherer<>(creatureStream, fieldValueToFilter, N);
        List<MythicalCreature> creatures = creatureGatherer.gather(500, MythicalCreature::getType);
        creatures = filterByParameterB(creatures, MythicalCreature::getYearsSinceFirstAppearance, minB, maxB);
        groupAndPrintResults(creatures, MythicalCreature::getType);
        resultList = creatures;
        break;
      default:
        System.out.println("Неправильний варіант.");
        return;
    }

    // Аналіз за полем Г (оригінальна частина)
    System.out.println("\nДослідження значень поля Г:");
    List<Double> gValues = resultList.stream()
        .map(item -> ((HasGField) item).getGField()) // Перетворення до значення поля Г
        .sorted()
        .collect(Collectors.toList());
    System.out.println(gValues);

    double Q1 = getPercentile(gValues, 25);
    double Q3 = getPercentile(gValues, 75);
    double IQR = Q3 - Q1;

    double lowerBound = Q1 - 1.5 * IQR;
    double upperBound = Q3 + 1.5 * IQR;

    Map<String, Long> groupedData = gValues.stream()
        .collect(Collectors.groupingBy(
            value -> (value < lowerBound || value > upperBound) ? "outliers" : "data",
            Collectors.counting()
        ));

    System.out.println(groupedData);
  }

  /**
   * Filters a list based on a specified range for a given parameter B.
   *
   * @param list        the list to filter
   * @param getBField   a function that extracts the parameter B from the list items
   * @param minB       the minimum value for filtering
   * @param maxB       the maximum value for filtering
   * @param <T>        the type of the items in the list
   * @return a filtered list containing only items within the specified range
   */
  private static <T> List<T> filterByParameterB(List<T> list, ToDoubleFunction<T> getBField,
      double minB, double maxB) {
    return list.stream()
        .filter(item -> {
          double bValue = getBField.applyAsDouble(item);
          return bValue >= minB && bValue <= maxB;
        })
        .collect(Collectors.toList());
  }

  /**
   * Groups the results by a specified field and prints the counts.
   *
   * @param list        the list to group
   * @param getVField   a function that extracts the field V from the list items
   * @param <T>        the type of the items in the list
   */
  private static <T> void groupAndPrintResults(List<T> list, Function<T, String> getVField) {
    Map<String, Long> groupedByFieldV = list.stream()
        .collect(Collectors.groupingBy(getVField, Collectors.counting()));

    System.out.println("\nГрупування результатів за Полем В:");
    groupedByFieldV.forEach((key, count) -> System.out.println(key + ": " + count));
  }

  /**
   * Calculates the specified percentile from a sorted list of values.
   *
   * @param sortedValues a sorted list of double values
   * @param percentile   the desired percentile to calculate (e.g., 25 for Q1)
   * @return the calculated percentile value
   */
  private static double getPercentile(List<Double> sortedValues, double percentile) {
    int index = (int) Math.ceil(percentile / 100.0 * sortedValues.size()) - 1;
    return sortedValues.get(index);
  }
}
