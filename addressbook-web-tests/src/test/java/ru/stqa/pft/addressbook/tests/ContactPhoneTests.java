package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactPhoneTests extends TestBase {

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
      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }
    private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));
    }
    private static String cleaned(String phone) {
      return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
  }