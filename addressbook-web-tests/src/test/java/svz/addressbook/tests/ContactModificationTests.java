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
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (! app.contact().isThereAContact()){
      app.contact().create(new ContactData().withFirstname("Vitaliy").withMiddlename("V.G.").withLastname("German").withMobilephone("+79651234567").withEmail("vgermanrus@gmail.com").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Vitaliy1").withMiddlename("V.G.").withLastname("German").withMobilephone("+79651234567").withEmail("vgermanrus@gmail.com").withGroup("test1");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
