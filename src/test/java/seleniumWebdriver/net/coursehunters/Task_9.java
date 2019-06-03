package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Task_9 extends MainSeleniumTest {
    /*Сделайте сценарии, которые проверяют сортировку стран и геозон (штатов) в учебном приложении litecart.
    1) на странице http://localhost/litecart/admin/?app=countries&doc=countries
    а) проверить, что страны расположены в алфавитном порядке
    б) для тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в
    алфавитном порядке
    2) на странице http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones
    зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
    */


    @Test
    void countriesTest() {
        loginToAdminPanel();
        //        Go to page
        chromeDriver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
//        wait while page Title will contains search result
        wait.until(ExpectedConditions.titleContains("My Test Store – Countries"));
        //        find countries webelements and add them to the list
        int countiesRowsQuantity = (chromeDriver.findElements(By.xpath("//tbody/tr"))).size();
        System.out.println("Found " + countiesRowsQuantity + " country names.");
//        create countries names list and set (as auto sorted collection)
        List<String> countryNamesList = new ArrayList<>();
        Set<String> countryNamesSet = new TreeSet<>();
//        add each country name to collections
        for (int i = 1; i <= countiesRowsQuantity; i++) {
            countryNamesList.add(chromeDriver.findElement((By.xpath("//tbody/tr[" + i + "]/td[5]"))).getText());
            countryNamesSet.add(chromeDriver.findElement((By.xpath("//tbody/tr[" + i + "]/td[5]"))).getText());
//            check Zones quantity for each country
            if (Integer.parseInt(chromeDriver.findElement((By.xpath("//tbody/tr[" + i + "]/td[6]"))).getText()) != 0) {
                System.out.println(chromeDriver.findElement((By.xpath("//tbody/tr[" + i + "]/td[5]"))).getText() + " country have more then 0 zones");
//                go to country zones page
                chromeDriver.findElement((By.xpath("//tbody/tr[" + i + "]/td[5]/a"))).click();
//                create zones list and set
                List<WebElement> zonesWebelementList = chromeDriver.findElements(By.xpath("//tbody/tr"));
                List<String> zonesNamesList = new ArrayList<>();
                Set<String> zonesNamesSet = new TreeSet<>();
//                add data to zones collections
                for (WebElement zonesWebElement : zonesWebelementList) {
                    zonesNamesList.add(zonesWebElement.findElement((By.xpath("./td[3]/input"))).getAttribute("value"));
                    zonesNamesSet.add(zonesWebElement.findElement((By.xpath("./td[3]/input"))).getAttribute("value"));
                }
//                compare zones collections
                Assertions.assertEquals(new ArrayList<>(zonesNamesSet), zonesNamesList);
                chromeDriver.navigate().back();
            }
        }
//        compare countries collections
        Assertions.assertEquals(new ArrayList<>(countryNamesSet), countryNamesList);
    }

    @Test
    void geoZonesTest() {
        loginToAdminPanel();
        //        Go to page
        chromeDriver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
//        wait while page Title will contains search result
        wait.until(ExpectedConditions.titleContains("My Test Store – Geo Zones"));
        //        find geozones webelements and add them to the list
        List<WebElement> geoZonesWebElements = chromeDriver.findElements(By.xpath("//tbody/tr"));
        System.out.println("Found " + geoZonesWebElements.size() + " geo zones.");
//        create geo zones names list and set (as auto sorted collection)
        List<String> geoZonesNamesList = new ArrayList<>();
        Set<String> geoZonesNamesSet = new TreeSet<>();
//        add each country name to collections
        for (WebElement geoZoneWebElement : geoZonesWebElements) {
            geoZonesNamesList.add(geoZoneWebElement.findElement(By.xpath("./td[3]")).getText());
            geoZonesNamesSet.add(geoZoneWebElement.findElement(By.xpath("./td[3]")).getText());
//            compare geo zones collections
            Assertions.assertEquals(new ArrayList<>(geoZonesNamesSet), geoZonesNamesList);

        }
    }
}
