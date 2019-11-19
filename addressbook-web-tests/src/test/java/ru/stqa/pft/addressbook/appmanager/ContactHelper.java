package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }
  public void editContact(int index) {
    WebElement checkbox =  wd.findElements(By.name("selected[]")).get(index);
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

   public void updateContact() {
    click(By.xpath("//input[@value='Update']"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      ContactData contact = new ContactData(id, firstName, lastName);
      contacts.add(contact);
    }
    return contacts;
  }
    public void fillContactForm (ContactData contactData){
      type(By.name("firstname"), contactData.getFirst_name());
      type(By.name("middlename"), contactData.getMiddle_name());
      type(By.name("lastname"), contactData.getLast_name());
      type(By.name("mobile"), contactData.getMobile_phone());
      type(By.name("email"), contactData.getEmail());

      if (contactData.getGroup() == null && contactData.getMiddle_name() == null && contactData.getMobile_phone() == null && contactData.getEmail() == null) {
        return;
      }
    }
  }