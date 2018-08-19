package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {


  @Test
  public void ContactModificationTests() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Vitaliy", "V.G.", "German", "+79651234567", "vgermanrus@gmail.com", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData(
            "Vitaliy",
            "V.G.",
            "German",
            "+7961111111",
            "vgermanrus@gmail.com",
            null),
            false
    );

    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
