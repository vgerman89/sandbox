package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void ContactDeletionTests(){
  app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Vitaliy", "V.G.", "German", "+79651234567", "vgermanrus@gmail.com", "test1"), true);
    }
  app.getContactHelper().selectContact();
  app.getContactHelper().deleteSelectedContacts();
  app.getContactHelper().deleteAlertAcception();
  app.getNavigationHelper().gotoHomePage();
  }
}
