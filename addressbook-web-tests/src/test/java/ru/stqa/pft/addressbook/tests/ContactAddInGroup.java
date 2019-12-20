package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.nio.file.attribute.GroupPrincipal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactAddInGroup extends TestBase {

  long now = System.currentTimeMillis();

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.getNavigationHelper().gotoContactList();
      File photo = new File("src/test/resources/stru.png");
      app.contactHelper().createContact(new ContactData().withFirstName("qwdqw" + now).withMiddleName("1212").
              withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719").withPhoto(photo), true);
      System.out.println("Precondition fulfilled - New contact created");
    }

    if (app.db().groups().size() == 0) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData().withName("test" + now));
      System.out.println("Precondition fulfilled - New contact group");
    }

//        Contacts contacts = app.db().contacts();
//        Groups groups = app.db().groups();
//        for (ContactData contact : contacts) {
//            if (contact.getGroups().size() == groups.size()) {
//                app.getNavigationHelper().gotoGroupPage();
//                //app.getGroupHelper().createGroup(new GroupData().withName("test" + now));
//                GroupData groupAdded = new GroupData().withName("test" + now);
//                app.getGroupHelper().createGroup(groupAdded);
//                System.out.println("Выполенно предусловие - Создана новая группа, в которой нет котактов");
//            }
//        }
  }

  @Test
  public void testContactAddGroup() {
    Contacts contactsBefore = app.db().contacts();
    Groups groupsBefore = app.db().groups();
    ContactData contact = app.db().contacts().iterator().next();

    if (contact.getGroups().size() != 0) {
      app.getNavigationHelper().gotoGroupPage();
      GroupData groupAdded = new GroupData().withName("test" + now).withFooter("df").withHeader("ef");
      app.getGroupHelper().createGroup(groupAdded);
      app.getNavigationHelper().gotoContactList();
      app.contactHelper().addToGroup(contact, groupAdded);
      System.out.println("Contact added in NEW group");
      Groups groupsAfter = app.db().groups();
      assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
      assertThat(groupsAfter, equalTo(groupsBefore.withAdded(groupAdded)));
    } else {
      app.getNavigationHelper().gotoGroupPage();
      GroupData group = app.db().groups().iterator().next();
      app.getNavigationHelper().gotoContactList();
      app.contactHelper().addToGroup(contact, group);
      System.out.println("Contact added in group");
      Groups groupsAfter = app.db().groups();
      assertThat(groupsAfter.size(), equalTo(groupsBefore.size()));
      assertThat(groupsAfter, equalTo(groupsBefore.withAdded(group)));

    }
  }
}