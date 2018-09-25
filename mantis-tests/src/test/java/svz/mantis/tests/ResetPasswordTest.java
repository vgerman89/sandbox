package svz.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class ResetPasswordTest extends TestBase {

  @Test
  public void resetPasswordTest() {
    //получаем данные о пользователях из БД
    //запускаем почтовый сервер с нужным адресом
    app.session().login("administrator", "root");
    app.goTo().managePage();
    app.goTo().manageUserPage();
    //Кликаем на второго из списка, т.к. первый администратор. Подставляем ID в ссылку юзера
    //Кликаем на кнопку сброса пароля
    //Ждем письма на почте
    //Переходим по ссылке из письма
    //Задаем новый пароль
    //Проверяем что юзер может залогиниться под новым паролем (через HTTP протокол, как в LoginTest)

  }

}
