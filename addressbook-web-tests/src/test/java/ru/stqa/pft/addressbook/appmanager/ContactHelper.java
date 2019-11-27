package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void createContact(ContactData contactData, boolean creation) {
    click(By.linkText("add new"));
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobilephone());
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
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }
  public void deletedContact(ContactData contact) {
    selectContactById(contact.getId());
    deletedContact();
  }
  public void editContact(int index) {
    WebElement checkbox =  wd.findElements(By.name("selected[]")).get(index);
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }
  public void editContactById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
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

      public void fillContactForm (ContactData contactData){
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("middlename"), contactData.getMiddlename());
      type(By.name("lastname"), contactData.getLastname());
      type(By.name("mobile"), contactData.getMobilephone());
      type(By.name("email"), contactData.getEmail());

        if (contactData.getGroup() == null && contactData.getMiddlename() == null
                && contactData.getMobilephone() == null && contactData.getEmail() == null)
                 {
          return;
        }
      }
 // public List<ContactData> getContactList() {
//
//        List<ContactData> contacts = new ArrayList<ContactData>();
//        List<WebElement> rows = wd.findElements(By.name("entry"));
//        for (WebElement row : rows) {
//            List<WebElement> cells = row.findElements(By.tagName("td"));
//            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
//            String lastName = cells.get(1).getText();
//            String firstName = cells.get(2).getText();
//            ContactData contact = new ContactData(id, firstName, lastName);
//            contacts.add(contact);
//        }
//        return contacts;
//    }

    public Contacts all() {
      Contacts contacts = new Contacts();
      List<WebElement> rows = wd.findElements(By.name("entry"));
      for (WebElement row : rows) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
        String lastName = cells.get(1).getText();
        String firstName = cells.get(2).getText();
        contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
      }
      return contacts;
    }
  }
