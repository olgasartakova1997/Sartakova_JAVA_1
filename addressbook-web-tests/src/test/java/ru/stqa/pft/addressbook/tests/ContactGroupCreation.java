package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class ContactGroupCreation extends TestBase {
  @Test(enabled=false)

    public void testContactCreate() throws Exception {
    app.getNavigationHelper().gotoContactList();
    List<ContactData> before = app.contactHelper().getContactList();
    ContactData contact = new ContactData("Sartakova", "Olga", "Evgenyvna","sara", "9556468355", "sartakova@mail.ru", null );
    app.contactHelper().createContact(contact, true);
    List<ContactData> after = app.contactHelper().getContactList();
      app.getSessionHelper().logout();

    Assert.assertEquals(after.size(), before.size() + 1);

    //after.remove(contact);
    before.add(contact);
    Comparator<? super ContactData> first_name = Comparator.comparing(ContactData::getFirst_name);
    before.sort(first_name);
    after.sort(first_name);
    Assert.assertEquals(before, after);
    }
  }

