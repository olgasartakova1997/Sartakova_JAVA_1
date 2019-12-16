package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
       if (app.db().contacts().size() == 0) {
        app.getNavigationHelper().homePage();
        app.contactHelper().createContact(new ContactData().withFirstName("qwdqw221").withMiddleName("1212").
                withLastName("lastLol").withNickname("nikLol").withMobilePhone("548568719"), true);
      }
    }
    @Test
    public void testContactPhones() {
      app.getNavigationHelper().homePage();
      ContactData contact = app.contactHelper().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contactHelper().intoFromEditForm(contact);
      assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }
    private static String cleaned(String address) {
      return address.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
  }