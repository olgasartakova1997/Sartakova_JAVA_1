package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeleted(){
    app.getNavigationHelper().gotoContactList();
    app.contactHelper().selectContact();
    app.contactHelper().deletedContact();
    app.contactHelper().alertAccept();
    app.getNavigationHelper().gotoContactList();
    app.getSessionHelper().logout();
  }
}