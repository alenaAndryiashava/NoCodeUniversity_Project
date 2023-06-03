package ui;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;


import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    String noCodeUniversityUrl = "https://jere237.softr.app/";

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        open(noCodeUniversityUrl);

    }

}
