package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if ((app.db().contacts().size() == 0)) {
      app.getNavigationHelper().gotoContactList();
      app.contactHelper().createContact(new ContactData().withFirstName("lol").withMiddleName("midLol").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719"), true);
    }
  }

  @Test
  public void testContactDeleted() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contactHelper().deletedContact(deletedContact);
    app.contactHelper().alertAccept();
    app.getNavigationHelper().gotoContactList();
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.withOut(deletedContact)));
  }
}