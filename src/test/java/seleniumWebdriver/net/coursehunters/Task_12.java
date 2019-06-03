package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Task_12 extends MainSeleniumTest {
    /*Задание 12. Сделайте сценарий добавления товара
    Сделайте сценарий для добавления нового товара (продукта) в учебном приложении litecart (в админке).
    Для добавления товара нужно открыть меню Catalog, в правом верхнем углу нажать кнопку "Add New Product", заполнить поля с информацией о
    товаре и сохранить.
    Достаточно заполнить только информацию на вкладках General, Information и Prices. Скидки (Campains) на вкладке Prices можно не добавлять.
    После сохранения товара нужно убедиться, что он появился в каталоге (в админке). Клиентскую часть магазина можно не проверять.
    Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
    */


    @Test
    void addProductTest() {
//        login
        loginToAdminPanel();
//        maximize window
        chromeDriver.manage().window().maximize();
//        open click on Catalog menu item
        chromeDriver.findElement(By.xpath("//li[@id='app-catalog']")).click();
//        click "Add product" button
        chromeDriver.findElement(By.xpath("//li/*[text()=' Add New Product']")).click();
//        fill form
//        set name
        String productName = "TestProduct" + ((int) (Math.random() * 100));
        fillDataField("//input[@name='name[en]']", productName);
//        set Unisex gender checkbox
        chromeDriver.findElement(By.xpath("//label[text()='Gender']/following-sibling::div[3]/label")).click();
//        add product image filepath
        chromeDriver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys("D:\\[IDEA]\\Sources\\Selenium src\\10Ochar.jpg");
//        select from Manufacturer dropdown list
        selectFromDropDownList("Manufacturer", "ACME Corp.");


//        go to next tab - Information
        chromeDriver.findElement(By.xpath("//ul[@class='nav nav-tabs']/li[2]")).click();
//        fill Short Description
        fillDataField("//input[@name='short_description[en]']", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
//        fill Description
        fillDataField("//*[@class='trumbowyg-editor trumbowyg-autogrow-on-enter']", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Fusce auctor convallis neque congue semper. Nulla vel vehicula nunc. Phasellus efficitur dolor nisi, in sagittis lectus " +
                "pellentesque et. In hac habitasse platea dictumst. Donec fermentum arcu ullamcorper metus sollicitudin vulputate. Integer " +
                "dolor ex, malesuada a libero quis, luctus pretium nisi. Nullam condimentum condimentum fermentum.");
//        move cursor to Save button /scroll down
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//*[@value='Save']"))).perform();
//        fill Attributes
        fillDataField("//textarea[@name='attributes[en]']", "Material: Kryptonite");
//        fill Head Title
        fillDataField("//input[@name='head_title[en]']", "Lorem ipsum dolor sit");
//        fill Meta Description
        fillDataField("//input[@name='meta_description[en]']", "Lorem ipsum dolor sit");


//        move cursor to Price tab /scroll up
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//ul[@class='nav nav-tabs']/li[3]"))).perform();
//        go to next tab - Price
        chromeDriver.findElement(By.xpath("//ul[@class='nav nav-tabs']/li[3]")).click();
//        fill Purchase Price
        fillDataField("//input[@name='purchase_price']", "1,75");
        selectFromDropDownList("Purchase Price", "US Dollars");
//        move cursor to Save button and click
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//*[@value='Save']"))).perform();
        chromeDriver.findElement(By.xpath("//*[@value='Save']")).click();


//        check system message appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        Assertions.assertTrue(chromeDriver.findElement(By.className("alert-success")).getText().contains("Changes saved successfully"));
//        check system message appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(" semi-transparent")));
        Assertions.assertTrue(chromeDriver.findElement(By.xpath("//*[text()='" + productName + "']")).isDisplayed());
    }
}
