package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests2 extends TestBase {
@Test
public void testGroupCreation() throws Exception {
  app.goTo().groupPage();
  List<GroupData> before = app.group().list();
  GroupData group = new GroupData("sart2", null, null);
  app.group().create(group);
  app.getSessionHelper().logout();
  List<GroupData> after = app.group().list();
  Assert.assertEquals(after.size(), before.size() + 1);

  before.add(group);
  Comparator<? super GroupData> byId = (q1, q2)->Integer.compare(q1.getId(),q2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals( before, after);
 }
}