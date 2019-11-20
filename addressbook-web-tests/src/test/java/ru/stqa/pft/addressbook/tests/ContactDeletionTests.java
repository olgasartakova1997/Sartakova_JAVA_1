package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test(enabled=false)
     public void testContactDeleted () {
      app.goTo().gotoContactList();
      if (! app.contactHelper().isThereAContact()) {
        app.contactHelper().createContact(new ContactData("Izmailova", "Marina", "Petrovna",
                "mery", "9632556534", "izmailova@mail.ru", null), true);
      }
      List<ContactData> before = app.contactHelper().getContactList();
      app.contactHelper().selectContact(before.size() - 1);
      app.contactHelper().deletedContact();
      app.contactHelper().alertAccept();
      app.goTo().gotoContactList();
      List<ContactData> after = app.contactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() - 1);
      app.getSessionHelper().logout();


    Comparator<? super ContactData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    }
  }
