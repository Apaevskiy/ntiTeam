package ru.paevskiy.ntiTeam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlanetPage {
    private final WebDriver driver;
    private final By inputName = By.xpath("//input[@id='nameOfPlanet']");
    private final By inputAge = By.xpath("//input[@id='lord.id']");
    private final By buttonCreate = By.xpath("//input[@type='submit']");
    private final By actualLink = By.xpath("//a[text()='testPlanet2']");

    public PlanetPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:8080/planets");
    }
    public boolean createPlanet(){
        WebElement link = driver.findElement(By.xpath("//a[@href='/planets/new']"));
        link.click();
        WebElement name = driver.findElement(inputName);
        WebElement age = driver.findElement(inputAge);
        name.sendKeys("testPlanet2");
        age.sendKeys("0");
        WebElement button = driver.findElement(buttonCreate);
        button.click();
        WebElement actualLord = driver.findElement(actualLink);
        return actualLord!=null;
    }
    public boolean setLord(){
        WebElement idElement = driver.findElement(By.xpath("//tr[td[not(node())]][1]/td[1]"));
        String id = idElement.getText();
        WebElement link = driver.findElement(By.xpath("//a[@href='/planets/"+id+"']"));
        link.click();
        WebElement edit = driver.findElement(By.xpath("//a[text()='Edit']"));
        edit.click();
        WebElement age = driver.findElement(By.xpath("//input[@id='lord']"));
        age.sendKeys("2");
        WebElement button = driver.findElement(buttonCreate);
        button.click();
        WebElement actualLord = driver.findElement(By.xpath("//tr[td[a[@href='/planets/"+id+"']]][1]/td[3]"));
        return actualLord!=null && !actualLord.getText().isEmpty();
    }
}
