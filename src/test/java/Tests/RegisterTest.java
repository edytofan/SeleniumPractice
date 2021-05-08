package Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegisterTest {

    //declaram o variabila WebDriver
    public WebDriver driver;

    @Test
    public void Register(){
        //setam driverul de chrome
        System.setProperty("webdriver.chrome.driver","C:\\Automation\\chromedriver.exe");
        //deschidem un browser de chrome
        driver=new ChromeDriver();
        //accesam un url
        driver.get("http://demo.automationtesting.in/Register.html");
        //intra full screen
        driver.manage().window().maximize();
        //facem refresh la pagina
        driver.navigate().refresh();

        //validam pagina pe care ne aflam
        //de fiecare data cand intram pe o pagina trebuie sa o validam
        String ExpectedRegisterPageTitle="Register";
        String ActualRegisterPageTitle=driver.getTitle();
        Assert.assertEquals("Pagina register nu are titlu corect",ExpectedRegisterPageTitle,ActualRegisterPageTitle);

        //ca sa identificam un web element trebuie sa ii gasim selectorul comun
        //un web element se poate identifica dupa: ID,class, orice atribut unic(xpath)
        //structura xpath: //prim-cuvant(MOV)[@selector='valoare']=> 1/1
        //1.identific element
        //2.specific actiunea
        WebElement First_Name_Web=driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        String First_Name_value="Eduard";
        First_Name_Web.sendKeys(First_Name_value);

        WebElement Last_Name_Web=driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String Last_Name_value="Tofan";
        Last_Name_Web.sendKeys(Last_Name_value);

        WebElement AdressWeb=driver.findElement(By.xpath("//textarea[@ng-model='Adress']"));
        String AdressValue="Cluj,Cluj-Napoca,Erich Bergel 32";
        AdressWeb.sendKeys(AdressValue);

        WebElement EmailWeb=driver.findElement(By.xpath("//input[@type='email']"));
        String EmailValue=""+System.currentTimeMillis()+"@gmail.com";
        EmailWeb.sendKeys(EmailValue);

        WebElement PhoneWeb=driver.findElement(By.xpath("//input[@type='tel']"));
        String PhoneValue=""+System.currentTimeMillis();
        String NewPhoneValue=PhoneValue.substring(0,10);
        PhoneWeb.sendKeys(NewPhoneValue);

        WebElement Gender_Web=driver.findElement(By.xpath("//input[@value='Male']"));
        Gender_Web.click();

        WebElement HobbyWeb=driver.findElement(By.id("checkbox1"));
        HobbyWeb.click();

        WebElement SkillWeb=driver.findElement(By.id("Skills"));
        String SkillValue="Java";
        Select SkillSelect=new Select(SkillWeb);
        SkillSelect.selectByVisibleText(SkillValue);

        WebElement CountryWeb=driver.findElement(By.id("countries"));
        String CountryValue="Romania";
        Select CountrySelect=new Select(CountryWeb);
        CountrySelect.selectByVisibleText(CountryValue);

        WebElement YearWeb=driver.findElement(By.id("yearbox"));
        String YearValue="2003";
        Select YearSelect=new Select(YearWeb);
        YearSelect.selectByVisibleText(YearValue);

        //inchid browser la finalul tesstului
        //driver.quit();
        //driver quit inchide browserul
        //driver close inchide tabul curent

        WebElement MonthWeb=driver.findElement(By.xpath("//select[@ng-model='monthbox']"));
        String MonthValue="November";
        Select MonthSelect=new Select(MonthWeb);
        MonthSelect.selectByValue(MonthValue);

        WebElement DayWeb=driver.findElement(By.xpath("//select[@ng-model='daybox']"));
        String DayValue="26";
        Select DaySelect=new Select(DayWeb);
        DaySelect.selectByValue(DayValue);

        WebElement FirstPassdWeb=driver.findElement(By.id("firstpassword"));
        String FirstPassValue="ASDFG";
        FirstPassdWeb.sendKeys(FirstPassValue);

        WebElement SPasswordWeb=driver.findElement(By.id("secondpassword"));
        String SPasswordValue="ASDFG";
        SPasswordWeb.sendKeys(SPasswordValue);

        //un drop down il recunoastem dupa cuvantul "select" din codul html
        //un drop down care nu are cuvantul "select"din codul html ii format din componente
        //1.Componenta pe care dam click
        WebElement LanguagesWeb=driver.findElement(By.id("msdd"));
        LanguagesWeb.click();
        //2.Componenta de pe care selectam valoarea dorita
        List<WebElement> LanguagesOptionsList=driver.findElements(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']/li/a"));
        for (int Index=0;Index<LanguagesOptionsList.size();Index++){
            String CurrentElementText=LanguagesOptionsList.get(Index).getText();
            if (CurrentElementText.equals("Romanian")) {
                LanguagesOptionsList.get(Index).click();
            }
        }
        Gender_Web.click();

        WebElement SelectCountryWeb=driver.findElement(By.xpath("//span[@role='combobox']"));
        SelectCountryWeb.click();
        List<WebElement> SelectCountryWebList=driver.findElements(By.xpath("//ul[@id='select2-country-results']/li"));
        for (int Index=0;Index<SelectCountryWebList.size();Index++){
            String CurentElementText=SelectCountryWebList.get(Index).getText();
            if (CurentElementText.equals("Japan")){
                SelectCountryWebList.get(Index).click();
                break;
            }
        }

        WebElement ChoseFileWeb=driver.findElement(By.id("imagesrc"));
        ChoseFileWeb.sendKeys("C:\\Users\\user\\Desktop\\kehasuk-shop-jojos-bizarre-adventure-art-poster-12.jpg");


        //driver.close();
        //Tema : dau click pe meniul de webtable + validez pagina web table
        //incerc dupa ce dau click pe buton sa fac refresh si dupa sa validez pagina
        //daca nu merge, ii spun sa mearga la un anumit url

        WebElement WebTableWeb=driver.findElement(By.xpath("//a[@href='WebTable.html']"));
        WebTableWeb.click();
        driver.navigate().to("http://demo.automationtesting.in/WebTable.html");
        String ExpectedWebTablePageTitle="Web Table";
        String ActualWebTablePageTitle=driver.getTitle();
        Assert.assertEquals("Pagina WebTable nu are titlu corect",ExpectedWebTablePageTitle,ActualWebTablePageTitle);
        //driver.navigate().refresh();
        //driver.close();
    }
}
