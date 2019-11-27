package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupCreation extends TestBase {
  @Test

    public void testContactCreate() throws Exception {
    app.goTo().gotoContactList();
    Contacts before = app.contactHelper().all();
    ContactData contact = new ContactData().withFirstName("sartasarta").withLastName("olyaolya").
            withMiddleName("EVG").withNickname("saranaboy").withMobilePhone("964216245");
    app.contactHelper().createContact(contact, true);
    Contacts after = app.contactHelper().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    //after.remove(contact);
    assertThat(after, equalTo(before.withAdded
            (contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
  }

