package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {


  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String login, String password) {
    type(By.name("user"), login);
    type(By.name("pass"), password);
    wd.findElement(By.id("LoginForm")).submit();
  }
  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

}