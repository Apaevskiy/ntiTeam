package ru.paevskiy.ntiTeam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LordPage {
    private final WebDriver driver;
    private final By linkToNewLord = By.xpath("//a[@href='/lords/new']");
    private final By inputName = By.xpath("//input[@id='name']");
    private final By inputAge = By.xpath("//input[@id='age']");
    private final By buttonCreateLord = By.xpath("//input[@type='submit']");
    private final By actualLink = By.xpath("//a[text()='testLord2']");

    public LordPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:8080/lords");
    }
    public boolean createLord() {
        WebElement link = driver.findElement(linkToNewLord);
        link.click();
        WebElement name = driver.findElement(inputName);
        WebElement age = driver.findElement(inputAge);
        name.sendKeys("testLord2");
        age.sendKeys("22222");
        WebElement button = driver.findElement(buttonCreateLord);
        button.click();
        WebElement actualLord = driver.findElement(actualLink);
        return actualLord!=null;
    }
}
