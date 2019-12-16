package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
      if (app.db().groups().size() == 0) {
        app.getNavigationHelper().groupPage();
        app.getGroupHelper().create(new GroupData().withName("test1"));
      }
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < 1) {
        GroupData chosenGroup = app.db().groups().iterator().next();
        app.contactHelper().addToGroup(contact, chosenGroup);
      }
    }
    }

    @Test
    public void testGroupDeletion() {
      Groups before = app.db().groups();
      app.getNavigationHelper().groupPage();
      GroupData deletedGroup = before.iterator().next();
      app.getGroupHelper().delete(deletedGroup);
      assertThat(app.getGroupHelper().count(), equalTo(before.size() -1));
      Groups after = app.db().groups();

      assertThat(after, equalTo(before.withOut(deletedGroup)));
    }
  }