
package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String first_name;
  private final String middle_name;
  private final String last_name;
  private final String nickname;
  private final String mobile_phone;
  private final String email;


  public ContactData(String first_name, String middle_name, String last_name, String nickname,  String mobile_phone, String email) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.last_name = last_name;
    this.nickname = nickname;
    this.mobile_phone = mobile_phone;
    this.email = email;
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
  }