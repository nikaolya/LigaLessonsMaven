package homework11;

import com.codeborne.selenide.Configuration;

public class SelenideConfiguration {
    public static void setSelenideConfiguration(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        Configuration.holdBrowserOpen = true;
        Configuration.screenshots = false;
        Configuration.reportsFolder = "target/build/reports";
        Configuration.downloadsFolder = "target/build/downloads";
    }
}
