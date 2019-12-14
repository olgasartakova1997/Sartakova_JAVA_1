package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()  == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("sart1"));
    }
  }
  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("sart1").withHeader("sart2").withFooter("sart3");
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));

    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }
}