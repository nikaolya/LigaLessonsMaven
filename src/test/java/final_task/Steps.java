package final_task;

import com.codeborne.selenide.Selenide;
import org.testng.asserts.SoftAssert;

public class Steps {
    private PageHeader pageHeader;
    public SoftAssert softAssert;

    public Steps(){
        this.pageHeader = Selenide.page(new PageHeader());
        this.softAssert = new SoftAssert();
    }

    public void checkIfTabHasCorrectStatus(String tabTitle, ElementStatus expectedStatus){
        ElementStatus actualStatus = pageHeader.getTabStatus(tabTitle);
        softAssert.assertEquals(actualStatus, expectedStatus);
    }

}
