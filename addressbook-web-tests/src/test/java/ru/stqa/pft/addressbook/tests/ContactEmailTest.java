package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contacts().size() == 0) {
      app.getNavigationHelper().gotoContactList();
      app.contactHelper().createContact(new ContactData().withFirstName("qwdqw221").withMiddleName("1212").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719"), true);
    }
  }

  @Test
  public void testContactEmails() {
    app.getNavigationHelper().gotoContactList();
    ContactData contact = app.contactHelper().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contactHelper().intoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEmailTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String email) {
    return email.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
