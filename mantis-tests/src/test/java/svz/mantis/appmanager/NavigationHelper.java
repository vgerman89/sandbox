package svz.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.0/manage_overview_page.php']")).click();
  }

  public void manageUserPage() {
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.0/manage_user_page.php']")).click();
  }
}
