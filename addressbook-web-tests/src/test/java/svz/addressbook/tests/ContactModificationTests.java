package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Vitaliy")
              .withMiddleName("V.G.")
              .withLastName("German")
              .withAddress("address")
              .withHomePhone("+7495000")
              .withMobilePhone("+79651234567")
              .withWorkPhone("+7499000")
              .withEmail("vgermanrus@gmail.com")
              .withEmail2("email2")
              .withEmail3("email3")
              //.withGroup("test 1")
              , true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Vitaliy1")
            .withMiddleName("V.G.")
            .withLastName("German")
            .withAddress("address1")
            .withHomePhone("+7495001")
            .withMobilePhone("+79651234566")
            .withWorkPhone("+7499001")
            .withEmail("vgermanrus@gmail.com")
            .withEmail2("email22")
            .withEmail3("email33")
            //.withGroup("test 1")
            ;
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
