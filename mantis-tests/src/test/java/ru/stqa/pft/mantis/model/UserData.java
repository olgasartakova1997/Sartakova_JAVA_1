package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

public class UserData {

  private int id;

  private String name;

  private String email;

  @Override
  public String toString() {
    return "UserData{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }
}
