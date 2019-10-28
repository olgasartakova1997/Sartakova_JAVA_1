package ru.stqa.pft.addressbook.model;

public class GroupDataSart {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String address;
  private final String mobile;
  private final String email;

  public GroupDataSart(String firstname, String middlename, String lastname, String address, String mobile, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }
}
