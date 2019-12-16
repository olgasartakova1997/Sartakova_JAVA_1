package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroup extends TestBase{

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
  }

  @Test
  public void testContactDeleteGroup() {
    ContactData contactBefore = app.db().contacts().iterator().next();
    if (contactBefore.getGroups() != null) {
      Groups groupsBefore = contactBefore.getGroups();
      app.contactHelper().removeFromGroup(contactBefore);
      Contacts contactsAfter = app.db().contacts();
      for (ContactData contactAfter : contactsAfter) {
        if (contactAfter.getId() == contactBefore.getId()) {
          Groups groupsAfter = contactAfter.getGroups();
          assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
          groupsBefore.removeAll(groupsAfter);
          assertThat(groupsAfter, equalTo(
                  contactBefore.getGroups().withOut(groupsBefore.iterator().next())));
        }
      }
    }
  }
}