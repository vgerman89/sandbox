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
      ContactData contact = new ContactData().withFirstName("Vitaliy").withMiddleName("V.G.").withLastName("German").withHomePhone("111").withMobilePhone("+79651234567").withWorkPhone("333").withEmail("vgermanrus@gmail.com").withGroup("test1");
      app.contact().create((contact), true);
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    }
}
