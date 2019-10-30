package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ContactBase {

  protected final ContactManager cont = new ContactManager();

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    cont.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    cont.stop();
  }

  public ContactManager getCont() {
    return cont;
  }
}
