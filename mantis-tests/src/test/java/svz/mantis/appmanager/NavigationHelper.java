package svz.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void waitForElement (String id, String cssSelector) {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    if (id != "") {
      WebElement element = wait.until(
              ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    if (cssSelector != "") {
      WebElement element = wait.until(
              ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

  }

  public void managePage() {
    String menuButtonid = "menu-toggler";
    String managePageLink = "a[href='/mantisbt-2.17.0/manage_overview_page.php']";
    waitForElement(menuButtonid, "");
    wd.findElement(By.id(menuButtonid)).click();
    waitForElement("", managePageLink);
    wd.findElement(By.cssSelector(managePageLink)).click();
  }

  public void manageUserPage() {
    String userPageLink = "a[href='/mantisbt-2.17.0/manage_user_page.php']";
    waitForElement("", userPageLink);
    wd.findElement(By.cssSelector(userPageLink)).click();
  }


}
