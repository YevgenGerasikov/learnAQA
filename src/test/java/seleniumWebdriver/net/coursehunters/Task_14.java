package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task_14 extends MainSeleniumTest {
    /*Задание 14. Проверьте, что ссылки открываются в новом окне
    Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
    Сценарий должен состоять из следующих частей:
    1) зайти в админку
    2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
    3) открыть на редактирование какую-нибудь страну или начать создание новой
    4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
    Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank". Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
    Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.
    */
    @Test
    void openNewWindowsTest() {
        loginToAdminPanel();
//        open Countries tab
        chromeDriver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
//        click "Add New Country" btn
        chromeDriver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
//        get all elements with external links list and check new windows
        for (WebElement externalLink : chromeDriver.findElements(By.xpath("//form[@name='country_form']//*[@class='fa fa-external-link']/.."))) {
            checkExternalWindow(externalLink.getAttribute("href"));
        }
    }
}
