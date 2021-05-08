package Help;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementMethods {
    public WebDriver driver;

    public ElementMethods(WebDriver driver){
        this.driver=driver;
    }

    public void HoverElement(WebElement element){
        Actions Action= new Actions(driver);
        Action.moveToElement(element).build().perform();
    }
}
