package ru.stqa.pft.addressbook.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.*;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactGroupCreation extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((q) -> new Object[]{q}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreate(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/stru.png");
    app.getNavigationHelper().homePage();
    Contacts before = app.db().contacts();
    app.contactHelper().createContact(contact.withPhoto(photo).inGroup(groups.iterator().next()), true);
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size() + 1);

    assertThat(after, equalTo(before.withAdded
            (contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}