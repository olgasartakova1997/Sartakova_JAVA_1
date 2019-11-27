
package ru.stqa.pft.addressbook.model;
import java.util.Objects;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactData {
  private String firstName;
  private String middleName;
  private String lastName;
  private String nickname;
  private String mobilePhone;
  private String email;
  private int id;
  private String group;

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getFirstname() {
    return firstName;
  }

  public String getMiddlename() {
    return middleName;
  }

  public String getLastname() {
    return lastName;
  }

  public String getNickname() {
    
    return nickname;
  }

  public String getMobilephone() {
    return mobilePhone;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, lastName, id);
  }
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", id=" + id +
            '}';
  }

}

