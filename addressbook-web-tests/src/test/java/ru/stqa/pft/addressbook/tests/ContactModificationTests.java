package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoContactList();
    app.contactHelper().editContact();
    app.contactHelper().type(By.name("firstname"), "Petrova");
    app.contactHelper().type(By.name("lastname"), "Nicolaevna");
    app.contactHelper().type(By.name("mobile"), "91300025830");
    app.contactHelper().updateContact();
    app.getNavigationHelper().gotoContactList();
    app.getSessionHelper().logout();
  }
}
