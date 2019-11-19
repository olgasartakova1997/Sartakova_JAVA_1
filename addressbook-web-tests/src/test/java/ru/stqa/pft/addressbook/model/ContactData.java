
package ru.stqa.pft.addressbook.model;
import java.util.Objects;

public class ContactData {
  private final String first_name;
  private final String middle_name;
  private final String last_name;
  private final String nickname;
  private final String mobile_phone;
  private final String email;
  private int id;
  private String group;

  public ContactData(int id, String first_name, String last_name) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.middle_name = null;
    this.nickname = null;
    this.mobile_phone = null;
    this.email = null;
    this.group = null;
  }

  public ContactData(String first_name, String middle_name, String last_name, String nickname,  String mobile_phone, String email, String group) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.last_name = last_name;
    this.nickname = nickname;
    this.mobile_phone = mobile_phone;
    this.email = email;
    this.group = group;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getMiddle_name() {
    return middle_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getNickname() {
    return nickname;
  }


  public String getMobile_phone() {
    return mobile_phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
  public int getId() {
    return id;
  }
  @Override
  public String toString() {
    return "ContactData{" +
            "first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(first_name, that.first_name) &&
            Objects.equals(last_name, that.last_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name);
  }
}
