package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    long now = System.currentTimeMillis();
    if (app.db().contacts().size() == 0) {
      app.getNavigationHelper().gotoContactList();
      File photo = new File("src/test/resources/stru.png");
      app.contactHelper().createContact(new ContactData().withFirstName("qwdqw" + now).withMiddleName("1212").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719").withPhoto(photo), true);
      System.out.println("Âûïîëåííî ïðåäóñëîâèå - Ñîçäàí íîâûé êîòàêò");
    }


    if (app.db().groups().size() == 0) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData().withName("test" + now));
      System.out.println("Âûïîëåííî ïðåäóñëîâèå - Ñîçäàíà íîâàÿ ãðóïïà");
    }
    Contacts contacts = app.db().contacts();
    GroupData group = app.db().groups().iterator().next();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() == 0) {
        app.getNavigationHelper().gotoContactList();
        app.contactHelper().addToGroup(contact, group);
        System.out.println("Âûïîëåííî ïðåäóñëîâèå - Êîíòàêò äîáàâëåí âãðóïïó");
      } return;
    } return;
  }

  @Test
  public void testContactDeleteGroup() {
    Contacts contactsBefore = app.db().contacts();
    for (ContactData contactBefore : contactsBefore) {
      if (contactBefore.getGroups() != null) {
        Groups groupsBefore = contactBefore.getGroups();
        app.getNavigationHelper().gotoContactList();
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
      } return;
    } return;
  }
}