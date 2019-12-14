package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0 ) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Olga").withLastName("Sartakova").withMobilePhone("945545454")
              .withEmail("osartakova@yandex.ru"), true);
    }
    if (app.db().groups().size() == 0 ){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("footer1").withHeader("header1"));
    }

  }
    @Test
  public void testContactDeleteFromGroup() {
    ContactData contactAfter = null;
    ContactData contactBefore = null;
    GroupData selectedGroup = null;
    ContactData selectedContact = null;
    Groups groups = app.db().groups();
    Contacts Allcontacts = app.db().contacts();
    selectedContact= Allcontacts.iterator().next(); //случайный контакт для случай (selectedContact.getGroups().size() == 0)

    for (ContactData oneOfContactToDelete : Allcontacts) {
      Groups groupsOfContactToDelete = oneOfContactToDelete.getGroups();
      if (groupsOfContactToDelete.size() > 0) {
        selectedContact = oneOfContactToDelete;
        selectedGroup = selectedContact.getGroups().iterator().next(); //можно дальше не искать
        break;
      }
    }
    if (selectedContact.getGroups().size() == 0) {
      selectedGroup = groups.iterator().next(); //берем рандомную группу. И далее она будет единственная с включеным контактом. Можно заново не искать.
      app.contact().addInGroupFinal(selectedContact, selectedGroup);
    }

      Contacts allContactsBefore = app.db().contacts(); // обновили т.к contactBefore = selectedContact но без ID группы.
      for (ContactData OneOfContactBefore : allContactsBefore) {
        if (OneOfContactBefore.getId() == selectedContact.getId()) {
          contactBefore = OneOfContactBefore;
          break;
        }
      }
      app.goTo().homePage();

      app.contact().deleteFromGroupFinal(selectedContact, selectedGroup);

      // получаем данные для сравнения после

      Contacts allContactsAfter = app.db().contacts(); //еще раз обновили
      for (ContactData OneOfContactAfter : allContactsAfter) {
        if (OneOfContactAfter.getId() == selectedContact.getId()) {
          contactAfter = OneOfContactAfter;
          break;
        }
      }
      //проверки
      assertEquals(contactBefore.getGroups().size(), contactAfter.getGroups().size() + 1);
      assertThat(contactBefore.getGroups(), equalTo(contactAfter.getGroups().withAdded(selectedGroup)));
    }
}