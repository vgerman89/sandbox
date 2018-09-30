package svz.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import svz.mantis.appmanager.HttpSession;
import svz.mantis.model.MailMessage;
import svz.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

  @Test
  public void resetPasswordTest() throws MessagingException, IOException, InterruptedException {
    //получаем данные о пользователях из БД
    List<UserData> usersList= app.db().usersList();
    String user = String.format(usersList.get(2).getUsername());
    System.out.println(user);
    String email = String.format(usersList.get(2).getEmail());
    String password = "password";
    String passwordNew = "passwordNew";
    app.session().login("administrator", "root");
    //Переходим на страницу со списком юзеров
    app.goTo().managePage();
    app.goTo().manageUserPage();
    //Кликаем на нужного юзера, и кликаем сбросить пароль
    app.user().passwordReset(usersList.get(2));
    //Ждем письма на почте
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 600000);
    //Переходим по ссылке из письма
    String passwordResetLink = findPasswordResetLink(mailMessages, email);
    //Задаем новый пароль
    app.user().resetPasswordFinish(passwordResetLink, passwordNew);
    //Проверяем что юзер может залогиниться под новым паролем (через HTTP протокол, как в LoginTest)
    HttpSession session = app.newSession();
    assertTrue(session.login(user, passwordNew));
    assertTrue(session.isLoggedInAs(user));


  }

  private String findPasswordResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
