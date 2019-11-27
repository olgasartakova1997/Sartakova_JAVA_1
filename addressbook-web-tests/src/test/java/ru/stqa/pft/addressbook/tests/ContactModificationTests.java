package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().gotoContactList();
    if (app.contactHelper().all().size() == 0) {
      app.contactHelper().createContact(new ContactData().withFirstName("1q2w3e").withMiddleName("r4e3w2").
              withLastName("ghjcvb").withNickname("8u7y6t").withMobilePhone("36142578"), true);
    }
  }
    @Test
    public void testContactModification() {

      Contacts before = app.contactHelper().all();
      ContactData modifiedContact = before.iterator().next();
      app.contactHelper().selectContactById(modifiedContact.getId());
      app.contactHelper().editContactById(modifiedContact.getId());
      ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("3659").
              withLastName("jhbjh");
    app.contactHelper().fillContactForm(contact);
    app.contactHelper().updateContact();
    app.goTo().gotoContactList();

    Contacts after = app.contactHelper().all();

    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}
