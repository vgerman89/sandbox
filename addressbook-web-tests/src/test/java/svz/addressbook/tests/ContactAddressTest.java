package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {

  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    System.out.println(contact.getAddress());
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    System.out.println(contactInfoFromEditForm.getAddress());
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
