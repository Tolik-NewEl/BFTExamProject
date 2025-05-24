package autotests.settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Configuration.browserCapabilities;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.headless = false;
        Configuration.browser = "chrome";
//        Configuration.browserVersion = "130.0";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 60000;
//        var options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
//        browserCapabilities = options;
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
