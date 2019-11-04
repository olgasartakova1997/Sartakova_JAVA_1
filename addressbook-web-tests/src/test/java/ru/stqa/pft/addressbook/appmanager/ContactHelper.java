package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void createContact(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirst_name());
    type(By.name("middlename"),contactData.getMiddle_name());
    type(By.name("lastname"),contactData.getLast_name());
    type(By.name("mobile"), contactData.getMobile_phone());
    type(By.name("email"), contactData.getEmail());
    click(By.name("submit"));
  }

  public void initCreateNewContact() {
    click(By.linkText("add new"));
  }
}
