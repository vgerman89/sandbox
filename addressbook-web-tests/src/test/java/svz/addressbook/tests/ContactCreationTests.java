package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().homePage();
      Contacts before = app.contact().all();
      ContactData contact = new ContactData().withFirstname("Vitaliy").withMiddlename("V.G.").withLastname("German").withMobilephone("+79651234567").withEmail("vgermanrus@gmail.com").withGroup("test1");
      app.contact().create((contact), true);
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    }
}
