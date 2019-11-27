package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().gotoContactList();
    if ((app.contactHelper().all().size() == 0)) {
      app.contactHelper().createContact(new ContactData().withFirstName("gdfgb").withMiddleName("fdbdsf").
              withLastName("khgfgbdf").withNickname("dfvxf").withMobilePhone("3344499"), true);
    }
  }
    @Test
    public void testContactDeleted() {
      Contacts before = app.contactHelper().all();
      ContactData deletedContact = before.iterator().next();
      app.contactHelper().deletedContact(deletedContact);
      app.contactHelper().alertAccept();
      app.goTo().gotoContactList();
      Contacts after = app.contactHelper().all();
      Assert.assertEquals(after.size(), before.size() - 1);

      assertThat(after, equalTo(before.withOut(deletedContact)));
    }
  }
