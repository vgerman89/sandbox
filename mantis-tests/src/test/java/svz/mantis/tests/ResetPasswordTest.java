package svz.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void resetPasswordTest() throws MessagingException, IOException, InterruptedException {
    List<UserData> usersList= app.db().usersList();
    String user = String.format(usersList.get(2).getUsername());
    String email = String.format(usersList.get(2).getEmail());
    String passwordNew = "passwordNew";
    app.session().login("administrator", "root");
    app.goTo().managePage();
    app.goTo().manageUserPage();
    app.user().passwordReset(usersList.get(2));
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    String passwordResetLink = findPasswordResetLink(mailMessages, email);
    app.user().resetPasswordFinish(passwordResetLink, passwordNew);
    HttpSession session = app.newSession();
    assertTrue(session.login(user, passwordNew));
    assertTrue(session.isLoggedInAs(user));
  }

  private String findPasswordResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
