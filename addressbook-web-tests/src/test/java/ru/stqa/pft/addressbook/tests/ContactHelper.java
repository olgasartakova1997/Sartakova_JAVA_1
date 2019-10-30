package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupDataSart;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(GroupDataSart groupDataSart) {
    type(By.name("firstname"),groupDataSart.getFirstname());
    type(By.name("middlename"),groupDataSart.getMiddlename());
    type(By.name("lastname"),groupDataSart.getLastname());
    type(By.name("address"),groupDataSart.getAddress());
    type(By.name("mobile"),groupDataSart.getMobile());
    type(By.name("email"),groupDataSart.getEmail());
  }

}
