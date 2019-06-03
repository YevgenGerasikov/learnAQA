package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Task_7 extends MainSeleniumTest {
    /*Задание 7. Сделайте сценарий, проходящий по всем разделам админки
    Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
    1) входит в панель администратора http://localhost/litecart/admin
    2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
    3) для каждой страницы проверяет наличие заголовка
    */

    @Test
    void checkMenuItems() {
        loginToAdminPanel();
//        find all menu items and add them to the Java list object
        List<WebElement> menuList = chromeDriver.findElements(By.cssSelector("ul#box-apps-menu li"));
//        create list of menu items id's
        List<String> menuId = new ArrayList<>();
        for (WebElement menuElement : menuList) {
            menuId.add(menuElement.getAttribute("id"));
        }
//        create cycle for sequential passage through all menu items
        for (String menuIdElement : menuId) {
            chromeDriver.findElement(By.id(menuIdElement)).click();
            //checking page title
            if (chromeDriver.getTitle().equals("")) {
                System.out.println("FAILED. Found page without title. Page URL is " + chromeDriver.getCurrentUrl());
            } else {
                System.out.println("Menu page title is " + chromeDriver.getTitle());
            }
//            check for submenu and add additional cycle
            if (chromeDriver.findElements(By.cssSelector("ul.docs li")).size() > 0) {
                List<WebElement> subMenuList = chromeDriver.findElements(By.cssSelector("ul.docs li"));
                List<String> subMenuId = new ArrayList<>();
                //fill subMenuId list
                for (WebElement submenuElement : subMenuList) {
                    subMenuId.add((submenuElement.getAttribute("id")));
                }
                //clicking submenu items
                for (String subMenuElementId : subMenuId) {
                    chromeDriver.findElement(By.id(subMenuElementId)).click();
                    //checking page title
                    if (chromeDriver.getTitle().equals("")) {
                        System.out.println("FAILED. Found page without title. Page URL is " + chromeDriver.getCurrentUrl());
                    } else {
                        System.out.println("Submenu page title is " + chromeDriver.getTitle());
                    }
                }
            }
        }
    }
}
