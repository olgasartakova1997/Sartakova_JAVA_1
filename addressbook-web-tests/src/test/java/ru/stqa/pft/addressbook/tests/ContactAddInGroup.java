package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddInGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();

    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 0"));
      app.goTo().homePage();
    }

    if (contacts.size() == 0) {
      ContactData contact = new ContactData().withFirstName("Olga").withLastName("AddToGroup");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }

    if (app.contact().findContactWithoutGroup(contacts) == null) {
      ContactData contact = new ContactData().withFirstName("Olga").withLastName("FreeContact");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testAddContactToGroup() {
    // Список до манипуляций
    Groups groups = app.db().groups();

    Contacts contactsDB = app.db().contacts();
    ContactData contactWithoutGroup = app.contact().findContactWithoutGroup(contactsDB);

    Groups groupsBefore = contactWithoutGroup.getGroups();

    int contactId = contactWithoutGroup.getId();
    GroupData selectedGroup = groups.iterator().next();
    app.contact().addContactToGroup(contactWithoutGroup.getId(), selectedGroup.getId());

    Contacts contactAfter = app.db().getContactById(contactId);
    ContactData contactWithGroup = contactAfter.iterator().next();

    // Список после манипуляций
    Groups groupsAfter = contactWithGroup.getGroups();

    //assertThat(contactWithGroup, CoreMatchers.equalTo(contactWithoutGroup.inGroup(selectedGroup)));

    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(selectedGroup)));
  }
}