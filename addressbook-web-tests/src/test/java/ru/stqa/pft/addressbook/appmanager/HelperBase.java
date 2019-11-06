package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;

public class HelperBase {
  protected final WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }
  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }

  public void selectType(By locator, String sType) {
    wd.findElement(locator).click();
    new Select(wd.findElement(locator)).selectByVisibleText(sType);
    wd.findElement(locator).click();
  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  protected boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
  }
}
}