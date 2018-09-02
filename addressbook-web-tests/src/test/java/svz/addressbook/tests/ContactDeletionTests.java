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
    app.goTo().homePage();
    if (! app.contact().isThereAContact()){
      app.contact().create(new ContactData().withFirstname("Vitaliy").withMiddlename("V.G.").withLastname("German").withMobilephone("+79651234567").withEmail("vgermanrus@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
