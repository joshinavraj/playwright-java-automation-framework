package framework.pages;

import com.microsoft.playwright.Page;
import framework.base.BasePage;

public class HomePage extends BasePage {

    private final String loginLink = "a[href='/login']";

    public HomePage(Page page) {
        this.page = page;
    }

    public void openLoginPage() {
        page.locator(loginLink).click();
    }

    public boolean isHomePageDisplayed() {
        return page.title().contains("Demo Web Shop");
    }
}
