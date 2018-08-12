package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("Vitaliy", "V.G.", "German", "+79651234567", "vgermanrus@gmail.com"));
      app.getContactHelper().submitContactCreation();
    }
}
