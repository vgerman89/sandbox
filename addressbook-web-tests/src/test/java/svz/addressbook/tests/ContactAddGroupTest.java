package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;
import svz.addressbook.model.GroupData;

public class ContactAddGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    //Проверяем существует ли группа, если нет - создаем
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1"));
    }

    //Проверяем существует ли контакт, если нет - создаем
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
                      .withEmail3("email3"), true);
    }
  }

  @Test
  public void testContactAddGroup() {
    app.goTo().homePage();
    Contacts contactOne = app.db().contacts();
    ContactData modifiedContact = contactOne.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId());
    app.contact().addGroup(contact);
    app.goTo().homePage();
  }

}
