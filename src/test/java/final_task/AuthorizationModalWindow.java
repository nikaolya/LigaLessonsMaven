package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class AuthorizationModalWindow {

    private final String XPATH_MODAL_WINDOW = "//div[contains(@class, 'modal-layout__push-close')]";
    private final String XPATH_NESTED_LOGIN_FORM = "//form[contains(@class, 'login-form')]";
    private final String XPATH_NESTED_CLOSE_BUTTON = "//button[contains(@class, 'login-form-top-nav__btn_close')]";
    private final String XPATH_NESTED_TITLE = "//h2[contains(@class, 'login-form__header')]";
    private final String XPATH_NESTED_INPUT_FIELD = "//div[contains(@class, 'form-field__input-container')]";
    private final String XPATH_NESTED_CONTINUE_BUTTON = "//button[contains(@class, 'login-form__button')]";
    private final String XPATH_NESTED_LINK = "//button[contains(@class, 'login-form__link')]";

    private SelenideElement closeButton;
    private SelenideElement title;
    private SelenideElement inputField;
    private SelenideElement continueButton;
    private SelenideElement link;

    public AuthorizationModalWindow(){
        closeButton = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_CLOSE_BUTTON);
        title = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_TITLE);
        inputField = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_INPUT_FIELD);
        continueButton = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_CONTINUE_BUTTON);
        link = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_LINK);
    }

    public boolean closeButtonIsDisplayed(){
        closeButton.should(Condition.exist);
        return closeButton.isDisplayed();
    }

    public String getModalWindowTitle(){
        title.should(Condition.exist);
        if (title.isDisplayed()){
            return title.getText();
        }
        return "failed";
    }

    public String checkInputField(){
        inputField.should(Condition.exist);
        if (inputField.isDisplayed()){
            SelenideElement placeholder = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_INPUT_FIELD+"//span");
            return placeholder.getText();
        }
        return "failed";
    }

    public ElementStatus getContinueButtonStatus(){
        continueButton.should(Condition.exist);
        if (continueButton.has(Condition.attribute("disabled"))){
            if (continueButton.getAttribute("disabled").equals("true")){
                return ElementStatus.DISABLED;
            }
        }
        return ElementStatus.ACTIVE;
    }

    public String getContinueButtonText(){
        continueButton.should(Condition.exist);
        SelenideElement text = Selenide.$x(XPATH_MODAL_WINDOW+XPATH_NESTED_LOGIN_FORM+XPATH_NESTED_CONTINUE_BUTTON+"/div");
        return text.getText();
    }

    public String getLinkText(){
        link.should(Condition.exist);
        if (link.isDisplayed()){
            return link.getText();
        }
        return "failed";
    }


}
