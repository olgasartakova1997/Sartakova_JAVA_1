package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size() == 0) {
      app.getNavigationHelper().gotoContactList();
      app.contactHelper().createContact(new ContactData().withFirstName("qwdqw221").withMiddleName("1212").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719"), true);
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contactHelper().selectContactById(modifiedContact.getId());
    app.contactHelper().editContactById(modifiedContact.getId());
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("3d2d").
            withLastName("r2f2").withMiddleName("kj");
    app.contactHelper().fillContactForm(contact);
    app.contactHelper().updateContact();
    app.getNavigationHelper().gotoContactList();
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

  }
}