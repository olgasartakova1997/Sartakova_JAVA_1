package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
     public void testContactDeleted () {
      app.getNavigationHelper().gotoContactList();
      if (! app.contactHelper().isThereAContact()) {
        app.contactHelper().createContact(new ContactData("Izmailova", "Marina", "Petrovna",
                "mery", "9632556534", "izmailova@mail.ru", null), true);
      }
      app.contactHelper().selectContact();
      app.contactHelper().deletedContact();
      app.contactHelper().alertAccept();
      app.getNavigationHelper().gotoContactList();
      app.getSessionHelper().logout();
    }
  }
