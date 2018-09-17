package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;
import svz.addressbook.model.GroupData;
import svz.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveGroupTest  extends TestBase {
  @BeforeMethod
  //Проверяем существует ли группа, если нет - создаем
  public void ensurePreconditions() {
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
  public void testContactRemoveGroup() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedGroup = before.iterator().next();
    Groups group = deletedGroup.getGroups();
    System.out.println("GROUPS + " + group);
    app.contact().removeGroup(deletedGroup);
    Contacts after = app.db().contacts();
    System.out.println("BEFORE +" + before);
    System.out.println("AFTER +" + after);
    //assertThat(after, equalTo(before.without(deletedGroup)));
    app.goTo().homePage();
  }
}
