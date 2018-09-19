package svz.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import svz.addressbook.model.ContactData;
import svz.addressbook.model.GroupData;
import svz.addressbook.model.Groups;


import java.util.List;

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
    //Проверяем, есть ли контакты не добавленные в группы, если нет - создаем новый без групп
    int i = 0;
    List<ContactData> contactsList = app.db().contactsList();
    for (ContactData contact : contactsList) {
      if (contact.getGroups().size() == 0 ){
        i ++;
      }
    }
    if (i == 0) {
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
    Groups allGroups = app.db().groups();
    GroupData addedGroup = allGroups.iterator().next();
    int i = 0;
    List<ContactData> contactsListBefore = app.db().contactsList();
    for (ContactData contact : contactsListBefore) {
      if (contact.getGroups().size() == 0) break;
       else if (contact.getGroups().size() > 0 ){
        i ++;
      }
    }
    ContactData modifiedContactBefore = contactsListBefore.get(i);
    Groups groupsBefore = modifiedContactBefore.getGroups();
    ContactData contact = new ContactData()
            .withId(modifiedContactBefore.getId()).inGroup(addedGroup);

    app.goTo().homePage();
    app.contact().addGroup(contact);
    List<ContactData> contactsListAfter = app.db().contactsList();
    ContactData modifiedContactAfter = contactsListAfter.get(i);
    Groups groupsAfter  = modifiedContactAfter.getGroups();
    assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
    assertThat(groupsAfter, equalTo(groupsBefore.withAdded(addedGroup)));
    app.goTo().homePage();
  }
}
