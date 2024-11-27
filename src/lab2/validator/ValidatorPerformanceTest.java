package lab2.validator;

public class ValidatorPerformanceTest {

  public static void main(String[] args) {
    ValidatorDemo obj = new ValidatorDemo("John", 25, "fkrokf");

    // Замір часу для валідації з використанням рефлексії
    long startTimeReflection = System.nanoTime();
    Validator validator = new Validator();
    validator.validate(obj);
    long endTimeReflection = System.nanoTime();
    long durationReflection = endTimeReflection - startTimeReflection;
    System.out.println("Time with Reflection: " + durationReflection + " nanoseconds");

    // Замір часу для валідації без використання рефлексії
    long startTimeNonReflection = System.nanoTime();
    NonReflectionValidator nonReflectionValidator = new NonReflectionValidator();
    nonReflectionValidator.validate(obj);
    long endTimeNonReflection = System.nanoTime();
    long durationNonReflection = endTimeNonReflection - startTimeNonReflection;
    System.out.println("Time without Reflection: " + durationNonReflection + " nanoseconds");
  }
}
