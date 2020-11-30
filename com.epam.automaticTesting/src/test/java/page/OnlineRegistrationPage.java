package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class OnlineRegistrationPage
{
    private static final String RegistrationForm_URL = "https://profile.onliner.by/registration";
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[5]/div/div/div/div/input")
    private WebElement login;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[6]/div/div/div/div/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[8]/div/div/div[1]/div/input")
    private WebElement passwordConfirm;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[9]/button")
    private WebElement  registrationButton;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[9]")
    private WebElement registerError;


    public OnlineRegistrationPage(WebDriver driver)
    {
       this.driver = driver;
       PageFactory.initElements(driver, this);
    }

    public OnlineRegistrationPage openPage()
    {
        this.driver.get(RegistrationForm_URL);
        this.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        return this;
    }

    public void  registerUser(String login, String password, String passwordConfirm)
    {
        waitForElementToBeClickable(this.driver, By.className("auth-form"));
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        this.passwordConfirm.sendKeys(passwordConfirm);

        registrationButton.click();
    }

    public OnlineRegistrationPage registerUserError()
    {
        registerUser("hguihggjjh@gmail.com", "HelloWorld12345ZAZA", "HelloWorld12345ZAZA");
        return new OnlineRegistrationPage(driver);
    }

    public OnlineRegistrationPage checkErrorMessage(String errorMessage)
    {
        Assert.assertFalse(registerError.getText().contains(errorMessage),
                "Error: " + errorMessage);
        return this;
    }

    /* получим все ошибки */
    public String checkAllErrorMessage()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"container\"]/div/div/div/form/div[2]/div/div[9]")));
        String userName = registerError.getText();
        return userName;
    }

    private  WebElement waitForElementToBeClickable(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(by));
    }
}