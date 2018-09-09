package svz.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;

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
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

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

  public ContactData infoFromEditForm (ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    returnToContactPage();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withAddress(address)
            .withHomePhone(homephone).withMobilePhone(mobilephone).withWorkPhone(workphone).withEmail(email)
            .withEmail2(email2).withEmail3(email3);
  }

  public void initContactModificationById(int id) {
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
      String address = tds.get(3).getText();
      String allPhones = tds.get(5).getText();
      String allEmails = tds.get(4).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).withAddress(address)
              .withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return contacts;
  }
}
