package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.GroupData;
import svz.addressbook.model.Groups;

import java.util.List;

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
    //Проверяем, есть ли контакты добавленные в группы, если нет - добавляем в первую попавшуюся группу
    int i = 0;
    List<ContactData> contactsList = app.db().contactsList();
    for (ContactData contact : contactsList) {
      if (contact.getGroups().size() > 0 ) break;
      else {
        i ++;
      }
    }
    if (i == contactsList.size()) {
      Groups allGroups = app.db().groups();
      GroupData addedGroup = allGroups.iterator().next();
      ContactData modifiedContactBefore = contactsList.get(0);
      ContactData contact = new ContactData()
              .withId(modifiedContactBefore.getId()).inGroup(addedGroup);
      app.goTo().homePage();
      app.contact().addGroup(contact);
    }
  }

  @Test
  public void testContactRemoveGroup() {
    int i = 0;
    List<ContactData> contactsList = app.db().contactsList();
    for (ContactData contact : contactsList) {
      if (contact.getGroups().size() > 0 ) break;
      else {
        i ++;
      }
    }
    app.goTo().homePage();
    ContactData modifiedContactBefore = contactsList.get(i);
    Groups groupsBefore = modifiedContactBefore.getGroups();
    app.contact().removeGroup(modifiedContactBefore);
    List<ContactData> contactsListAfter = app.db().contactsList();
    ContactData modifiedContactAfter = contactsListAfter.get(i);
    Groups groupsAfter  = modifiedContactAfter.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
    assertThat(groupsAfter, equalTo(groupsBefore.without(modifiedContactBefore.getGroups().iterator().next())));
    app.goTo().homePage();
  }
}
