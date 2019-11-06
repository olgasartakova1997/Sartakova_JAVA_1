package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoContactList();
    if (!app.contactHelper().isThereAContact()) {
      app.contactHelper().createContact(new ContactData("sartakova", "olga", "evgenyevna",
              "sara", "35435", "sara@jhdj",  null), false);
    }
    app.contactHelper().editContact();
    app.contactHelper().createContact(new ContactData("Petrova", "Olgaaa", "Nikolaevna","sara", "9556468355", "sarta@mail.ru" ,null), false);
    app.contactHelper().updateContact();
    app.getNavigationHelper().gotoContactList();
    app.getSessionHelper().logout();
  }
}
