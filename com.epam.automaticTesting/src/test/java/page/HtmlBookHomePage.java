package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HtmlBookHomePage
{
    private static final String HOMEPAGE_URL = "https://html5book.ru/html-html5/";
    private WebDriver driver;
    private WebElement keyPress;

    @FindBy(className = "search-field")
    private WebElement searchInput;

    @FindBy(xpath = "/html/body/header/div/div/div[2]/i")
    private WebElement elementToClickable;

    public HtmlBookHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HtmlBookHomePage openPage()
    {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public MixedSearchResults searchForTerms(String term)
    {
        elementToClickable.click();
        searchInput.sendKeys(term);
        searchInput.sendKeys(Keys.ENTER);
        return new MixedSearchResults(driver, term);
    }
}
