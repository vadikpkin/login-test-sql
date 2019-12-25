package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import util.Dao;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.*;

public class AuthPage {
    private static final SelenideElement authCode = $("[name='code']");
    private static final SelenideElement submitBtn = $("[data-test-id='action-verify']");
    private static final SelenideElement inputSubCode = $(".input__sub");
    private static final SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private static final SelenideElement errorNotificationContent = $(".notification__content");


    public DashBoardPage setAuthCode() throws SQLException {
        authCode.setValue(Dao.getAuthCode(Dao.getId(DataHelper.getValidLoginInfo().getLogin())));
        submitBtn.click();
        return new DashBoardPage();
    }

    public void checkDeclineEmptyCode() {
        submitBtn.click();
        inputSubCode.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void checkDeclineInvalidCode() {
        authCode.setValue("343431434");
        submitBtn.click();
        errorNotification.shouldHave(Condition.visible);
        errorNotificationContent.shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."));
    }

    public void checkDeclineThreeTimesInvalidLogin() throws SQLException {
        authCode.setValue(Dao.getAuthCode(Dao.getId(DataHelper.getValidLoginInfo().getLogin())));
        submitBtn.click();
        errorNotification.shouldHave(Condition.visible);
        errorNotificationContent.shouldHave(Condition.text("Превышено"));
    }
}

