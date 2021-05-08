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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WindowTest {
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
        WebElement FramesMeniuWeb=driver.findElement(By.xpath("//a[contains(text(),'Windows')]"));
        FramesMeniuWeb.click();

        driver.switchTo().frame("aswift_2");
        List<WebElement> checkPopUpDisplayList=driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
        if (checkPopUpDisplayList.size()>0){
            new WebDriverWait(driver,15).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ad_iframe"));
            WebElement ClosePopupWeb=driver.findElement(By.xpath("//span[contains(text(),'Close')]"));
            ClosePopupWeb.click();
        }
        driver.switchTo().defaultContent();

        List<WebElement> WindowsButtons=driver.findElements(By.xpath("//ul[@class='nav nav-tabs nav-stacked']/li/a"));
        WindowsButtons.get(0).click();

        WebElement ClickOptionButton=driver.findElement(By.xpath("//button[contains(text(),'click')]"));
        ClickOptionButton.click();

        List<String> OpenTabs=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(OpenTabs.get(1));
        System.out.println("Titlul paginii este"+driver.getTitle());
        driver.close();
        driver.switchTo().window(OpenTabs.get(0));
        System.out.println("Titlul paginii este"+driver.getTitle());

        driver.switchTo().defaultContent();
        WindowsButtons.get(1).click();

        WebElement ClickWindow=driver.findElement(By.xpath("//button[contains(text(),'click')]"));
        ClickWindow.click();

        List<String> OpenWindow=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(OpenWindow.get(1));
        System.out.println("Titlul paginii este"+driver.getTitle());
        driver.close();
        driver.switchTo().window(OpenTabs.get(0));
        System.out.println("Titlul paginii este"+driver.getTitle());

    }
}
