package svz.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() {
      app.getNavigationHelper().gotoHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      ContactData contact = new ContactData("Vitaliy", "V.G.", "German", "+79651234567", "vgermanrus@gmail.com", "test1");
      app.getContactHelper().createContact((contact), true);
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() + 1);

      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare((c1.getId()), c2.getId());
      before.sort(byId);
      after.sort(byId);


      Assert.assertEquals((before), (after));

    }
}
