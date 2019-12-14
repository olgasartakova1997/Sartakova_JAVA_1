package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import javax.persistence.Id;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactAddInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Olga").withLastName("Sartakova").withMobilePhone("+7625515415")
              .withEmail("osartakova@yandex.ru"), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("footer1").withHeader("header1"));
    }
  }

  @Test
  public void testContactAddInGroup() {
    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    ContactData selectedContact = null;
    GroupData selectedGroup = null;
    ContactData contactAfter = null;

    for (ContactData oneOfContactToAdd : allContacts) {
      Groups groupsOfContactToAdd = oneOfContactToAdd.getGroups();
      if (groupsOfContactToAdd.size() != allGroups.size()) {
        allGroups.removeAll(groupsOfContactToAdd); //находим свободную группу
        selectedGroup = allGroups.iterator().next(); //выбираем первую свободную
        selectedContact = oneOfContactToAdd; //присваеваем
        break; //чтобы не перебирать все
      }
    }
    if (selectedGroup == null) {
      ContactData contact = new ContactData()
              .withFirstName("new").withLastName("Sartakova").withMobilePhone("+7913258369")
              .withEmail("osartakova@yandex.ru");
      Contacts after = app.db().contacts();
      contact.withId(after.stream().mapToInt((g) -> (g).getId()).max().getAsInt()); //берем контакт с максимальным ID
      selectedContact = contact;  //далее selectedContact не изменяется и является контактом Before.
      selectedGroup = allGroups.iterator().next();
    }

    app.goTo().homePage();
    app.contact().allGroupsInContactPage();
    app.contact().addInGroupFinal(selectedContact, selectedGroup);

      //проверки
      Contacts allContactsAfter = app.db().contacts(); //заново получаем из БД инфу для сравнения.
      for (ContactData oneOfContactAfter : allContactsAfter) {
        if (oneOfContactAfter.getId() == selectedContact.getId()) { //ищем контакт с таким же ID
          contactAfter = oneOfContactAfter;
          break;
        }
      }
      assertEquals(selectedContact.getGroups().size(), contactAfter.getGroups().size() - 1);
      assertThat(selectedContact.getGroups(), equalTo(contactAfter.getGroups().without(selectedGroup)));
    }
  }
}