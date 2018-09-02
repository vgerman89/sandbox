package svz.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModificationById(int id) {
    //wd.findElements(By.xpath("//input[@name='selected[]']/../..//img[@title='Edit']")).get(index).click();
    wd.findElement(By.xpath("//input[@value='"+ id +"']/../..//img[@title='Edit']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));

  }

  public void deleteAlertAcception() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactPage();

  }

  public void modify(ContactData contact){
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm((contact), false);
    submitContactModification();
    returnToContactPage();
  }

  public void delete (ContactData contact){
    selectContactById(contact.getId());
    deleteSelectedContacts();
    deleteAlertAcception();
    returnToContactPage();
  }



  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.cssSelector("td input")).getAttribute("value"));

      List<WebElement> tds = element.findElements(By.tagName("td"));
      String firstname = tds.get(2).getText();
      String lastname = tds.get(1).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}
