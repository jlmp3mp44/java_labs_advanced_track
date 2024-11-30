package lab3.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lab2.annotations.MaxValue;
import lab2.annotations.MinValue;
import lab2.annotations.NotNull;
import lab2.annotations.StringLength;

/**
 * Represents a user in the system. It stores personal information about the user, such as name,
 * age, email, and user ID.
 */

@XmlRootElement
public class User {

  @NotNull
  private String userId;
  @NotNull
  @StringLength(maxLength = 50, min = 3)
  private String name;

  @NotNull
  @MinValue(18)
  @MaxValue(99)
  private Integer age;

  private String email;

  public User(String name, Integer age, String email, String userId) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.userId = userId;
  }

  public User() {

  }

  @XmlElement
  // Getters and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @XmlElement
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @XmlElement
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
