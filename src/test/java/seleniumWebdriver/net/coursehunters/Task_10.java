package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task_10 extends MainSeleniumTest {
    /*Задание 10. Проверить, что открывается правильная страница товара
    Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара в учебном приложении litecart.
    1) Открыть главную страницу
    2) Кликнуть по первому товару в категории Campaigns
    3) Проверить, что открывается страница правильного товара
    Более точно, проверить, что
    а) совпадает текст названия товара
    б) совпадает цена (обе цены)
    Кроме того, проверить стили цены на главной странице и на странице товара -- первая цена серая, зачёркнутая, маленькая, вторая цена
    красная жирная, крупная.
    */
    @Test
    void productPagePropertiesTest() {
//        create two list for product properties store
        List<String> mainPageProductPropertiesList = new ArrayList<>();
        List<String> secondPageProductPropertiesList = new ArrayList<>();

//        Open shop main page
        chromeDriver.navigate().to("http://localhost/litecart/");
//        maximize current window
        chromeDriver.manage().window().maximize();
//        wait while page Title will contains search result
        wait.until(ExpectedConditions.titleContains("My Test Store | Online Store"));
//        find product cards and add it to the list
        WebElement mainPageProduct = chromeDriver.findElement(By.xpath("//div[@id='campaign-products']//div[@data-id=1]"));
        //add main product properties to list
        mainPageProductPropertiesList.add(mainPageProduct.getAttribute("data-name"));
        mainPageProductPropertiesList.add(mainPageProduct.findElement(By.xpath(".//*[@class='regular-price']")).getText());
        mainPageProductPropertiesList.add(mainPageProduct.findElement(By.xpath(".//*[@class='campaign-price']")).getText());
        mainPageProductPropertiesList.add(mainPageProduct.findElement(By.xpath(".//*[@class='price-wrapper']/s")).getAttribute("class"));
        mainPageProductPropertiesList.add(mainPageProduct.findElement(By.xpath(".//*[@class='price-wrapper']/strong")).getAttribute("class"));

//        remember the identifier of the current window
        String originalWindow = chromeDriver.getWindowHandle();
//        open new window using JS script
        ((JavascriptExecutor) chromeDriver).executeScript("window.open()");
//        wait for window with new identifier
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> exitingWindows = new ArrayList<>(chromeDriver.getWindowHandles());
//        switch to new window
        String link = mainPageProduct.findElement(By.xpath("./a")).getAttribute("href");
        chromeDriver.switchTo().window(exitingWindows.get(1));
        chromeDriver.get(link);

//        find product and add it to the list
        WebElement secondPageProduct = chromeDriver.findElement(By.xpath("//div[@id='box-product' and @data-id=1]"));
//        add main product properties to list
        secondPageProductPropertiesList.add(secondPageProduct.getAttribute("data-name"));
        secondPageProductPropertiesList.add(secondPageProduct.findElement(By.xpath(".//*[@class='regular-price']")).getText());
        secondPageProductPropertiesList.add(secondPageProduct.findElement(By.xpath(".//*[@class='campaign-price']")).getText());
        secondPageProductPropertiesList.add(secondPageProduct.findElement(By.xpath(".//*[@class='price-wrapper']/del")).getAttribute("class"));
        secondPageProductPropertiesList.add(secondPageProduct.findElement(By.xpath(".//*[@class='price-wrapper']/strong")).getAttribute("class"));

//        compare lists
        Assertions.assertEquals(mainPageProductPropertiesList, secondPageProductPropertiesList);

//        close second window
        chromeDriver.close();
//        go to main window
        chromeDriver.switchTo().window(exitingWindows.get(0));
    }
}
