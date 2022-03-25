package tests;


import chromeDriver.GetChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;


import java.io.IOException;

import static contsants.Constants.MAINPAGE;

public class TestMain {
    private WebDriver driver;

    @BeforeMethod
    public void BeforeTest() {
        driver = GetChromeDriver.getChromeDriver();
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        SeleniumTutorialPage seleniumTutorialPage = new SeleniumTutorialPage(driver);
        PracticeSite2Page practiceSite2Page = new PracticeSite2Page(driver);
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
    }

    @Test(priority = 1)
    public void mainPageTest() throws InterruptedException {
        driver.get(MAINPAGE);
        MainPage mainPage = new MainPage(driver);
        mainPage.checkingElements();
        Thread.sleep(2000);
        String newBlockSlider = mainPage.blockSlider.getText();
        Assert.assertEquals(newBlockSlider, "LIFETIME MEMBERSHIP CLUB\n" +
                "FREE Access to All Ongoing and Future Live Trainings and 20+ Most demanding Automation courses with lifetime access\n" +
                "Learn More");
        mainPage.sliderButtonClick();
        String newCoursePanel = mainPage.coursePanel.getText();
        Assert.assertEquals(newCoursePanel, "Appium Mobile Automation Testing for Android and IOS\n" +
                "Get Started\n" +
                "Automation Architect Selenium with 7 live projects\n" +
                "Get Started\n" +
                "Protractor End to End testing for Angular JS App\n" +
                "Get Started");
    }

    @Test(priority = 2)
    public void registrationAndAuthorizationTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        SeleniumTutorialPage seleniumTutorialPage = new SeleniumTutorialPage(driver);
        driver.get(MAINPAGE);
        mainPage.logInButtonClick();
        authorizationPage.signUpButtonClick();
        registrationPage.registration();
        seleniumTutorialPage.logOut();
        authorizationPage.logIn();

    }

    @Test(priority = 3)
    public void goToAnotherPageTest() {
        MainPage mainPage = new MainPage(driver);
        driver.get(MAINPAGE);
        mainPage.careersButtonClick();
    }

    @Test(priority = 4)
    public void practiceSite2Authorization() throws InterruptedException, IOException {
        MainPage mainPage = new MainPage(driver);
        PracticeSite2Page practiceSite2Page = new PracticeSite2Page(driver);
        AuthorizationPracticeSite2Page authorizationPracticeSite2Page = new AuthorizationPracticeSite2Page(driver);
        driver.get(MAINPAGE);
        mainPage.goToPracticeSite2();
        practiceSite2Page.RegistrationButtonClick();
        authorizationPracticeSite2Page.authorizationPracticeSite2();
        String textLogIn = authorizationPracticeSite2Page.textLogIn.getText();
        Assert.assertEquals(textLogIn, "You're logged in!!", "Регистрация не прошла");

    }


    @AfterMethod
    public void close() {
        driver.quit();
    }
}
