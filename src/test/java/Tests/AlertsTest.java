package Tests;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlertsTest {

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

        WebElement SwitchToMenu=driver.findElement(By.xpath("//a[contains(text(),'Switch')]"));
        Actions Action=new Actions(driver);
        Action.moveToElement(SwitchToMenu).perform();

        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Frames')]")));
        WebElement FramesMeniuWeb=driver.findElement(By.xpath("//a[contains(text(),'Alerts')]"));
        FramesMeniuWeb.click();

        driver.switchTo().frame("aswift_2");
        List<WebElement> checkPopUpDisplayList=driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
        if (checkPopUpDisplayList.size()>0){
            new WebDriverWait(driver,15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ad_iframe"));
            WebElement ClosePopupWeb=driver.findElement(By.xpath("//span[contains(text(),'Close')]"));
            ClosePopupWeb.click();
        }
        driver.switchTo().defaultContent();
        //driver.navigate().to("http://demo.automationtesting.in/Alerts.html");

        String ExpectedAlertsPageTitle="Alerts";
        String ActualAlertsPageTitle=driver.getTitle();
        Assert.assertEquals("Pagina register nu are titlu corect",ExpectedAlertsPageTitle,ActualAlertsPageTitle);

        List<WebElement> AlertsButtons=driver.findElements(By.xpath("//ul[@class='nav nav-tabs nav-stacked']//li/a"));
        AlertsButtons.get(0).click();

        WebElement ClickOptionButton=driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
        ClickOptionButton.click();

        Alert AlertOk = driver.switchTo().alert();
        System.out.println(AlertOk.getText());
        AlertOk.accept();

        driver.switchTo().defaultContent();
        AlertsButtons.get(1).click();

        WebElement ClickOptionButtonCancel=driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        ClickOptionButtonCancel.click();

        Alert AlertCancel=driver.switchTo().alert();
        System.out.println(AlertCancel.getText());
        AlertCancel.dismiss();

        driver.switchTo().defaultContent();
        AlertsButtons.get(2).click();

        WebElement ClickOptionButtonText=driver.findElement(By.xpath("//button[@class='btn btn-info']"));
        ClickOptionButtonText.click();

        Alert AlertText=driver.switchTo().alert();
        System.out.println(AlertText.getText());
        AlertText.sendKeys("gata");
        AlertText.accept();

        driver.switchTo().defaultContent();
        //driver.quit();
    }
}