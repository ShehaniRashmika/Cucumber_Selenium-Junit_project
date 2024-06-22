package glue;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@RunWith(Cucumber.class)
public class W {
    private static W instance = null;

    public static W get() {
        if (instance == null) {
            instance = new W();
        }
        return instance;
    }

    protected WebDriver driver;

    private W() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "./chromedriver");

        String pathToDriver = System.getProperty("webdriver.chrome.driver");
        if (pathToDriver == null || pathToDriver.isEmpty()) {
            throw new RuntimeException("Define a path to the Chrome driver using system property 'webdriver.chrome.driver'");
        }

        // Initialize WebDriver only if the system property is set
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    public static void close() {
        if (instance != null) {
            instance.driver.quit();
            instance = null;
        }
    }
}
