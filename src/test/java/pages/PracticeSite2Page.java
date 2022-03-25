package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeSite2Page extends PageFactory {
    private WebDriver driver;
    @FindBy(xpath = "//h2[contains(text(),'Registration')]")
    private WebElement registrationButton;

    public PracticeSite2Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void RegistrationButtonClick() {
        registrationButton.click();
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
    }
}
