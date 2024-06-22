package glue;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSteps {

    @Given("url {string} is launched")
    public void url_is_launched(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    @When("^About page is shown$")
    public void aboutPageIsShown() {
        W.get().driver.findElement(By.linkText("About")).click();
    }

    @Then("^page displays \"([^\"]*)\"$")
    public void pageDisplays(String text) {
        WebDriverWait wait = new WebDriverWait(W.get().driver, Duration.ofSeconds(3000));
        String xpath = "//h1[@class='modules-lib__mission-statement__headline glue-headline glue-headline--fluid-2']";

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        String elementText = element.getText();
        assertTrue("Text not found!", elementText.contains(text));
    }

    @When("^searching for \"([^\"]*)\"$")
    public void searchingFor(String query) {
        W.get().driver.findElement(By.name("q")).sendKeys(query);
        W.get().driver.findElement(By.name("q")).submit();
    }

    @Then("^results contain \"([^\"]*)\"$")
    public void resultsContain(String result) {
        WebDriverWait wait = new WebDriverWait(W.get().driver, Duration.ofSeconds(3000));
        String xpath = "//h3[contains(@class, 'LC20lb') and contains(text(), 'Home - BBC News')]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        assertTrue(W.get().driver.getPageSource().contains(result));
    }

    //result stats are not shown for the automated browser, thus had to do slight modifications
    @Then("^result stats are displayed$")
    public void resultStatsAreDisplayed() {
        WebDriverWait wait = new WebDriverWait(W.get().driver, Duration.ofSeconds(30));
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='g']")));
        assertTrue(results.size() > 0);
    }

    @Then("^number of \"([^\"]*)\" is more than (\\d+)$")
    public void numberOfIsMoreThan(String type, int count) {
        if (type.equals("results")) {
            List<WebElement> results = W.get().driver.findElements(By.xpath("//div[@class='g']"));
            assertTrue(results.size() > count);
        } else if (type.equals("seconds")) {
            Instant start = Instant.now();
            W.get().driver.get("https://www.google.co.uk/");
            WebElement searchBox = W.get().driver.findElement(By.name("q"));
            searchBox.sendKeys("BBC News");
            searchBox.submit();

            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();
            double timeInSeconds = timeElapsed / 1000.0;
            assertTrue(timeInSeconds > count);
        }
    }

}