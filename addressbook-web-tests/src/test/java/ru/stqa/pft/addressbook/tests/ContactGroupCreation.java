package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

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
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);

    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List <ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // aka List GroupData.class
    return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("sart 1"));
    }
  }

    @Test (dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
      Contacts before = app.db().contacts();
   // ContactData contact = new ContactData()
      //      .withFirstName("Olga").withLastName("Sara").withAddress("NSK").withEmail("sartakovaa@yandex.com").withHomePhone("9625854854").withGroup("sart1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test
  public void testContactCreationWithPhoto() throws Exception {
    app.goTo().homePage();
    File photo = new File("src/test/resources/stru.jpg");
    ContactData contact = new ContactData()
            .withFirstName("Olga").withLastName("Sartakova").withPhoto(photo).withGroup("sart1");
    app.contact().create(contact, true);
    app.goTo().homePage();
  }
}
