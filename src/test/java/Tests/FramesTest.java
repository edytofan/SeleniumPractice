package Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FramesTest {

    public WebDriver driver;

    @Test
    public void TestAutomat(){
        System.setProperty("webdriver.chrome.driver","C:\\Automation\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("http://demo.automationtesting.in/Register.html");
        driver.manage().window().maximize();
        //wait implicit
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String ExpectedRegisterPageTitle="Register";
        String ActualRegisterPageTitle=driver.getTitle();
        Assert.assertEquals("Pagina register nu are titlu corect",ExpectedRegisterPageTitle,ActualRegisterPageTitle);

        //Daca avem un text salvat in codul html scris cu negru la capatul liniei inte">text<
        //Putem folosi contains cand identificam dupa xpath

        WebElement SwitchToMenu=driver.findElement(By.xpath("//a[contains(text(),'Switch')]"));
        Actions Action=new Actions(driver);
        Action.moveToElement(SwitchToMenu).perform();

        //declaram un wait explicit care sa astepte dupa element
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Frames')]")));
        WebElement FramesMeniuWeb=driver.findElement(By.xpath("//a[contains(text(),'Frames')]"));
        FramesMeniuWeb.click();
        //driver.navigate().to("http://demo.automationtesting.in/Frames.html");

        //Inchidem popup-ul
        driver.switchTo().frame("aswift_2");
        List<WebElement> checkPopUpDisplayList=driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
        if (checkPopUpDisplayList.size()>0){
            new WebDriverWait(driver,15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ad_iframe"));
            WebElement ClosePopupWeb=driver.findElement(By.xpath("//span[contains(text(),'Close')]"));
            ClosePopupWeb.click();
        }
        driver.switchTo().defaultContent();


        //Ne putem muta pe un Iframe dupa id sau webelement
        List<WebElement> FrameButtons=driver.findElements(By.xpath("//ul[@class='nav nav-tabs ']/li/a"));
        FrameButtons.get(0).click();

        driver.switchTo().frame("singleframe");
        WebElement InputSingleFrame=driver.findElement(By.xpath("//input[@type='text']"));
        InputSingleFrame.sendKeys("Ez");

        //dupa ce am terminat de lucrat  cu iframe trebuie sa ne mutam pe framul default
        driver.switchTo().defaultContent();
        FrameButtons.get(1).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']")));
        WebElement InputMultipleFrames=driver.findElement(By.xpath("//input[@type='text']"));
        InputMultipleFrames.sendKeys("Ez");
        driver.switchTo().defaultContent();
        //driver.quit();

    }

}
