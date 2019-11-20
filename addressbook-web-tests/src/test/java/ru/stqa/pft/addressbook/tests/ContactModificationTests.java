package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {
  @Test(enabled=false)
  public void testContactModification() {
    app.goTo().gotoContactList();
    if (!app.contactHelper().isThereAContact()) {
      app.contactHelper().createContact(new ContactData("sartakova", "olga", "evgenyevna",
              "sara", "35435", "sara@jhdj",  null), false);
    }
    List<ContactData> before = app.contactHelper().getContactList();
    app.contactHelper().selectContact(before.size() - 1);
    app.contactHelper().editContact(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "sarka", "456789");
    app.contactHelper().fillContactForm(contact);
    app.contactHelper().updateContact();
    app.goTo().gotoContactList();
    List<ContactData> after = app.contactHelper().getContactList();
    app.getSessionHelper().logout();

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
