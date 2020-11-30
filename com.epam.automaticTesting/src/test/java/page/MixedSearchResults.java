package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MixedSearchResults
{
    private final String splitRegex = "\\s";
    private WebDriver driver;
    private String searchTerm;

    @FindBy(className = "search-title")
    private List<WebElement> generalSearchResult;

    public MixedSearchResults(WebDriver driver, String searchTerm)
    {
        this.searchTerm = searchTerm;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int countGeneralNumberOfSearchResults()
    {
        System.out.println("Number of results:" + generalSearchResult.size());
        return  generalSearchResult.size();
    }

    public int countResultsNumberWithSearchTerm()
    {
        List<WebElement> resultsNumberWithSearchTerm = driver.findElements(By.id(""));
        System.out.println("Search result number for requested term:" + resultsNumberWithSearchTerm.size());
        return resultsNumberWithSearchTerm.size();
    }
}
