
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.DashBoardPage;
import pages.LoginPage;
import util.Dao;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    private static final String URL = "http://localhost:9999/";

    @BeforeEach
    void clearAuthCodes() throws SQLException {
        Dao.clearAuthCodes();
    }

    @AfterAll
    static void clearAll() throws SQLException {
        Dao.clearAllTables();
    }

    @Test
    void shouldSubmitLoginValidInfo() throws SQLException {
        open(URL);
        LoginPage loginPage = new LoginPage();
        AuthPage authPage = loginPage.loginValidInfo();
        DashBoardPage dashBoardPage = authPage.setAuthCode();
        dashBoardPage.checkPage();
    }

    @Test
    void shouldDeclineRequestInvalidInfo() {
        open(URL);
        LoginPage loginPage = new LoginPage();
        loginPage.loginInvalidInfo("kolya", "password");
    }

    @Test
    void shouldDeclineRequestEmptyInfo() {
        open(URL);
        LoginPage loginPage = new LoginPage();
        loginPage.checkSubs();
    }

    @Test
    void shouldDeclineRequestEmptyAuthCode() {
        open(URL);
        LoginPage loginPage = new LoginPage();
        AuthPage authPage = loginPage.loginValidInfo();
        authPage.checkDeclineEmptyCode();
    }

    @Test
    void shouldDeclineRequestInvalidCode() {
        open(URL);
        LoginPage loginPage = new LoginPage();
        AuthPage authPage = loginPage.loginValidInfo();
        authPage.checkDeclineInvalidCode();
    }

    @Test
    void shouldBlockAfterThreeTimesInvalidRequest() throws SQLException {
        open(URL);
        LoginPage loginPage = new LoginPage();
        loginPage.blockSystem();
        AuthPage authPage = loginPage.loginValidInfo();
        authPage.checkDeclineThreeTimesInvalidLogin();
    }

}
