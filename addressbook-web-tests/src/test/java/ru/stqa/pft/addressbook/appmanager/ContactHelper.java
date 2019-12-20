package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void createContact(ContactData contactData, boolean creation) {
    click(By.linkText("add new"));
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());
    selectType(By.name("bday"), contactData.getDay());
    selectType(By.name("bmonth"), contactData.getMonth());
    type(By.name("byear"), contactData.getYear());
    click(By.name("submit"));
    click(By.linkText("home page"));

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
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
    WebElement checkbox = wd.findElements(By.name("selected[]")).get(index);
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  public void editContactById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
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


  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    selectType(By.name("bday"), contactData.getDay());
    selectType(By.name("bmonth"), contactData.getMonth());
    type(By.name("byear"), contactData.getYear());

    if (contactData.getGroups() == null && contactData.getMiddleName() == null && contactData.getHomePhone() == null
            && contactData.getMobilePhone() == null && contactData.getEmail() == null && contactData.getDay() == null
            && contactData.getMonth() == null && contactData.getYear() == null && contactData.getPhoto() == null) {
      return;
    }
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }

  public ContactData intoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }

  public void selectGroupById(int id) {
    click(By.name("to_group"));
    WebElement select = wd.findElement(By.name("to_group"));
    select.click();
    select.findElement(By.cssSelector("option[value='" + id + "']")).click();
  }

  public void selectGroupByName(String name) {
    click(By.name("to_group"));
    WebElement select = wd.findElement(By.name("to_group"));
    select.click();
    select.findElement(By.xpath("//select[@name='to_group']/option[text()='" + name + "']")).click();
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupByName(group.getName());
    click(By.name("add"));
  }

  public void removeFromGroup(ContactData contact) {
    filterContactsByGroup(contact);
    selectContactById(contact.getId());
    click(By.name("remove"));
    click(By.linkText("home"));
    selectAllGroupsContacts();
  }

  private void filterContactsByGroup(ContactData contact) {
    WebElement select = wd.findElement(By.name("group"));
    select.click();
    select.findElement(By.cssSelector("option[value='" + contact.getGroups().iterator().next().getId() + "']")).click();
  }

  private void selectAllGroupsContacts() {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }

  public void initDeletionContactFromGroup(String groupName) {
    click(By.xpath("//input[@value='Remove from \""+groupName+"\"']"));
  }

  public void deletionContactFromGroup(ContactData contact, String groupName) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    selectContactById(contact.getId());
    initDeletionContactFromGroup(groupName);
    Assert.assertTrue(isElementPresent(By.xpath("//div[@class = 'msgbox' and contains(text(),'Users removed.')]")));
  }
}