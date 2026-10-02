package framework.pages;

import com.microsoft.playwright.Page;
import framework.base.BasePage;

public class LoginPage extends BasePage {

    private final String emailInput = "#Email";
    private final String passwordInput = "#Password";
    private final String loginButton = "input[value='Log in']";
    private final String logoutLink = "a[href='/logout']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public LoginPage enterEmail(String email) {
        page.locator(emailInput).fill(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        page.locator(passwordInput).fill(password);
        return this;
    }

    public void clickLogin() {
        page.locator(loginButton).click();
    }

    public boolean isLogoutDisplayed() {
        return page.locator(logoutLink).isVisible();
    }

    public void login(String email, String password) {
        enterEmail(email)
                .enterPassword(password)
                .clickLogin();
    }
}
