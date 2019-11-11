package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void createContact(ContactData contactData, boolean creation) {
    click(By.linkText("add new"));
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("middlename"), contactData.getMiddle_name());
    type(By.name("lastname"), contactData.getLast_name());
    type(By.name("mobile"), contactData.getMobile_phone());
    type(By.name("email"), contactData.getEmail());
    click(By.name("submit"));
    click(By.linkText("home page"));

    if (contactData.getGroup() == null) {
      return;
    } else {
      if (creation) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }
  public void initCreateNewContact() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {
    click(By.xpath("//input[@value='Update']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}