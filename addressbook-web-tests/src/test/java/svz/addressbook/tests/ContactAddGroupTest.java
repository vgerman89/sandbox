package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.Contacts;
import svz.addressbook.model.GroupData;
import svz.addressbook.model.Groups;

import java.security.acl.Group;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTest extends TestBase {
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
  public void testContactAddGroup() {
    app.goTo().homePage();
    Groups allGroups = app.db().groups();
    GroupData addedGroup = allGroups.iterator().next();
    Contacts before = app.db().contacts();
    ContactData modifiedContactBefore = before.iterator().next();
    Groups groupsBefore = modifiedContactBefore.getGroups();
    ContactData contact = new ContactData()
            .withId(modifiedContactBefore.getId()).inGroup(addedGroup);
    app.contact().addGroup(contact);
    Contacts after = app.db().contacts();
    ContactData modifiedContactAfter = after.iterator().next();
    Groups groupsAfter  = modifiedContactAfter.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(addedGroup)));
    app.goTo().homePage();
  }
}
