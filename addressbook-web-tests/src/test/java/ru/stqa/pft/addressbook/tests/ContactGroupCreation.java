package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDataSart;

public class ContactGroupCreation extends ContactBase {

  @Test
  public void testContactCreation() throws Exception {
    cont.getContactHelper().fillContactForm(
            new GroupDataSart("Olga", "Evgenyevna", "Sartakova", "Nowosibirsk", "89485785870", "sartakowa.olya@yandex.ru"));
    cont.getContactHelper().EnterContactCreation();
  }
}
