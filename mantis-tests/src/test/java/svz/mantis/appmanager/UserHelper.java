package svz.mantis.appmanager;

import org.openqa.selenium.By;
import svz.mantis.model.UserData;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void passwordReset(UserData user) {
    initUserModificationById(user.getId());
    initUserPasswordResetById();
  }


  public void initUserModificationById(int id) {
    wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id="+ id +"']")).click();
  }

  public void initUserPasswordResetById() {
    wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
  }

  public void resetPasswordFinish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}
