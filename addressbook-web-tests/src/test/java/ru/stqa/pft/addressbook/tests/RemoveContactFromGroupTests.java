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

public class RemoveContactFromGroupTests extends TestBase {

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
      ContactData contact = new ContactData()
              .withFirstName("Olga").withLastName("RemoveFromGroup").inGroup(groups.iterator().next());
      app.contact().create(contact, true);
      app.goTo().homePage();
    }

    if (app.contact().findContactWithGroup(contacts) == null) {
      app.contact().selectAllGroup();
      Contacts contactsUI = app.contact().all();
      ContactData selectedContactUI = contactsUI.iterator().next();
      GroupData selectedGroup = groups.iterator().next();
      app.contact().addContactToGroup(selectedContactUI.getId(), selectedGroup.getId());
      app.goTo().homePage();
    }
  }

  public void testRemoveContactFromGroup() {
    Contacts contacts = app.db().contacts();

    ContactData contactWithGroup = app.contact().findContactWithGroup(contacts);
    int contactId = contactWithGroup.getId();

    Groups groupsBefore = contactWithGroup.getGroups();

    GroupData group = contactWithGroup.getGroups().iterator().next();
    app.contact().filterByGroup(group.getId());
    app.contact().removeContactFromGroup(contactWithGroup.getId(), group.getId());

    Contacts contactAfter = app.db().getContactById(contactId);

    ContactData contactWithoutGroup = contactAfter.iterator().next();

    Groups groupsAfter = contactWithoutGroup.getGroups();

    //assertThat(contactWithGroup, CoreMatchers.equalTo(contactWithoutGroup.inGroup(deletedGroupData)));

    assertThat(groupsBefore, equalTo(groupsAfter.withAdded(group)));
  }
}
