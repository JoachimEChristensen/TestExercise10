/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeleniumTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Joachim
 */
public class SeleniumTest {

    WebDriver driver;

    List<WebElement> list;
    Select dropdown;
    WebElement btn;
    WebElement listObject;

    public SeleniumTest() {
        Path data_file = Paths.get("src/main/resources/chromedriver.exe");
        // Create an instance of the driver
        System.setProperty("webdriver.chrome.driver", data_file.toString());
        driver = new ChromeDriver();
        // Navigate to a web page
        driver.get("http://localhost:8080/Test-EX8-MarioWeb/");

        dropdown = new Select(driver.findElement(By.id("PizzaList")));
        btn = driver.findElement(By.id("btn"));
        listObject = driver.findElement(By.id("OrderList"));
    }

    @Test
    public void testInsert() {
        dropdown.selectByIndex(0);
        btn.click();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        list = listObject.findElements(By.tagName("li"));
        String result = list.get(0).getText();
        assertEquals(result, "Pizza 1 ordered at: " + time);

    }
}
