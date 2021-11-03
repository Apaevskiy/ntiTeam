package ru.paevskiy.ntiTeam;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.paevskiy.ntiTeam.pages.LordPage;
import ru.paevskiy.ntiTeam.pages.PlanetPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium {
    private static WebDriver webDriver;
    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(new ChromeOptions().addArguments("--lang=ru"));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void closeDriver(){
        webDriver.quit();
    }
    @Test
    public void createLord() {
        LordPage lordPage = new LordPage(webDriver);
        Assert.assertTrue(lordPage.createLord());
    }
    @Test
    public void createPlanet() {
        PlanetPage planetPage = new PlanetPage(webDriver);
        Assert.assertTrue(planetPage.createPlanet());
    }
    @Test
    public void setLord() {
        PlanetPage planetPage = new PlanetPage(webDriver);
        Assert.assertTrue(planetPage.setLord());
    }
    @Test
    public void getParasites() {
        webDriver.get("http://localhost:8080/parasites");
        List<WebElement> elements = webDriver.findElements(By.xpath("//tr"));
        Assert.assertTrue(elements.size()>0);
    }
    @Test
    public void getTop() {
        webDriver.get("http://localhost:8080/top");
        List<WebElement> elements = webDriver.findElements(By.xpath("//tr"));
        Assert.assertEquals(elements.size(), 11);
    }
}
