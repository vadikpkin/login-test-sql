package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class DashBoardPage {
    private static final SelenideElement header = $("[data-test-id='dashboard']");

    public void checkPage() {
        header.shouldHave(Condition.text("Личный кабинет"));
    }
}
