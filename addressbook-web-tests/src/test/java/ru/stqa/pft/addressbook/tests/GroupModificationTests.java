package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if(app.db().groups().size() == 0){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData().withName("test21"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("wdw").withFooter("wd").withHeader("q2");
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().modifyGroup(group);
    assertThat(app.getGroupHelper().getGroupCount(), equalTo(before.size()));
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }
}