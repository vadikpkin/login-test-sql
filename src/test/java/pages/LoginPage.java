package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private static final SelenideElement login = $("[name='login']");
    private static final SelenideElement pass = $("[name='password']");
    private static final SelenideElement submitBtn = $("[data-test-id='action-login']");
    private static final SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private static final SelenideElement inputSubLogin = $$(".input__sub").get(0);
    private static final SelenideElement inputSubPass = $$(".input__sub").get(1);

    public AuthPage loginValidInfo() {
        DataHelper.LoginInfo loginInfo = DataHelper.getValidLoginInfo();
        login.setValue(loginInfo.getLogin());
        pass.setValue(loginInfo.getPassword());
        submitBtn.click();
        return new AuthPage();
    }

    public void loginInvalidInfo(String loginStr, String passStr) {
        login.setValue(loginStr);
        pass.setValue(passStr);
        submitBtn.click();
        errorNotification.shouldHave(Condition.visible);
    }

    public void checkSubs() {
        submitBtn.click();
        inputSubLogin.shouldHave(Condition.text("Поле обязательно для заполнения"));
        inputSubPass.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void blockSystem() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginInvalidInfo("vasya", "qwer");
        submitBtn.click();
        login.setValue("\b\b\b\b\b\b\b\b\b\b");
        pass.setValue("\b\b\b\b\b\b\b\b\b\b");
        loginPage.loginInvalidInfo("vasya", "qwert");
        submitBtn.click();
        login.setValue("\b\b\b\b\b\b\b\b\b\b");
        pass.setValue("\b\b\b\b\b\b\b\b\b\b");
        loginPage.loginInvalidInfo("vasya", "qwerty");
        submitBtn.click();
        login.setValue("\b\b\b\b\b\b\b\b\b\b");
        pass.setValue("\b\b\b\b\b\b\b\b\b\b");
    }
}
