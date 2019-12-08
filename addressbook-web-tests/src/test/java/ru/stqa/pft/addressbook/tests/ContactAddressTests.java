package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{
  @BeforeMethod
  public void ensurePrecondition() {
    if (app.contact().all().size() != 0) {
      return;
    } else {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName("sart1"));
      }
      app.goTo().homePage();
      ContactData contact = new ContactData()
              .withFirstName("Olga").withLastName("Sartakova").withAddress("NSK").withEmail("sartakova@jfhbdjf.ru").withHomePhone("+3832658947").withGroup("sart1");
      app.contact().create(contact, true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(reformAddress(contactInfoFromEditForm)));

  }

  private String reformAddress(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}