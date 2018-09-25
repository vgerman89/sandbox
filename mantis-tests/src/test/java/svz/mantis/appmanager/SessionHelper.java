package svz.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super (app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    wd.findElement(By.cssSelector("input[value='Войти']")).click();
    type(By.name("password"), password);
    wd.findElement(By.cssSelector("input[value='Войти']")).click();
  }
}
