package svz.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() throws InterruptedException {
    /*try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/
    wd.findElement(By.id("menu-toggler")).click();
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.0/manage_overview_page.php']")).click();
    /*
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
    */
  }

  public void manageUserPage() {
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.0/manage_user_page.php']")).click();
  }
}
