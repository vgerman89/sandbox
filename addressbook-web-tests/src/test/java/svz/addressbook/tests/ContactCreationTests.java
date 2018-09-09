package svz.addressbook.tests;

import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      app.goTo().homePage();
      Contacts before = app.contact().all();
      File photo = new File("src/test/resources/enot.png");

      ContactData contact = new ContactData().withFirstName("Vitaliy").withMiddleName("V.G.").withLastName("German").withAddress("Russia, Moscow")
              .withHomePhone("+7 (111)").withMobilePhone("+7 965 123 45 67").withWorkPhone("33-33")
              .withEmail("vgermanrus@gmail.com").withEmail2("email2").withEmail3("email3")
              .withGroup("test1").withPhoto(photo);
      app.contact().create((contact), true);
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    }
}
