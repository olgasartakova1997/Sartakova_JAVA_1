package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.getNavigationHelper().homePage();
      app.contactHelper().createContact(new ContactData().withFirstName("qwdqw221").withMiddleName("1212").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719"), true);
    }
    if (app.db().groups().size() == 0) {
      app.getNavigationHelper().groupPage();
      app.getGroupHelper().create(new GroupData().withName("test21"));
    }
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == groups.size()) {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().create(new GroupData().withName("test21"));
      }
    }
  }

  @Test
  public void testContactAddGroup() {
    ContactData contactBefore = app.db().contacts().iterator().next(); // список контактов
    GroupData group = app.db().groups().iterator().next(); // случайна группа

    //for (ContactData contactBefore : contactsBefore) {
    if (!contactBefore.getGroups().contains(group)) {
      Groups groupsBefore = contactBefore.getGroups();
      app.contactHelper().addToGroup(contactBefore, group);
      app.getNavigationHelper().homePage();
      Contacts contactsAfter = app.db().contacts();
      for (ContactData contactAfter : contactsAfter) {
        if (contactAfter.getId() == contactBefore.getId()) {
          Groups groupsAfter = contactAfter.getGroups();
          assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
          assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));
        }
      }
    }
  }
}
