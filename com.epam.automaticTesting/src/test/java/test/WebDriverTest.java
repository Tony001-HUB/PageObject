package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.HtmlBookHomePage;
import page.OnlineRegistrationPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverTest {
    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @Test
    public void commonSearchTermResultsNotEmpty() {
        int expectedSearchResult = new HtmlBookHomePage(driver)
                .openPage()
                .searchForTerms("HTML-теги")
                .countGeneralNumberOfSearchResults();

        Assert.assertTrue(expectedSearchResult > 0, "search result are empty!");
    }

    @Test
    public void failedUserRegistration() {
        new OnlineRegistrationPage(driver)
                .openPage()
                .registerUserError()
                .checkErrorMessage("Такой пользователь уже зарегистрирован")
        ;
    }

    @AfterMethod(alwaysRun = true) //наглядно видим выполнение теста;
    public void screenShot() throws java.io.IOException {
        TakesScreenshot scr = ((TakesScreenshot) driver);
        File file1 = scr.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file1, new File("D:\\3 Курс\\EPAM\\com.epam.automaticTesting\\Screenshot.png"));
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
