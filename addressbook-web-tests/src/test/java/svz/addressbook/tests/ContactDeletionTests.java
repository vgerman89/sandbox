package svz.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void ContactDeletionTests(){
  app.getNavigationHelper().gotoHomePage();
  app.getContactHelper().selectContact();
  app.getContactHelper().deleteSelectedContacts();
  app.getContactHelper().deleteAlertAcception();
  app.getNavigationHelper().gotoHomePage();
  }
}
