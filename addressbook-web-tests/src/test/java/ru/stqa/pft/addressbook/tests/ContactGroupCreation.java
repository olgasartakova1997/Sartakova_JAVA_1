package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
public class ContactGroupCreation extends TestBase {
  @Test

    public void testContactCreate() throws Exception {
      app.contactHelper().initCreateNewContact();
      app.contactHelper().createContact(new ContactData("Sartakova", "Olga", "Evgenyvna","sara", "9556468355", "sartakova@mail.ru", "sart1" ), true);
      app.getNavigationHelper().homePageClick();
      app.getSessionHelper().logout();
    }
  }