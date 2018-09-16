package svz.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
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
              .withGroup("test 1"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}
