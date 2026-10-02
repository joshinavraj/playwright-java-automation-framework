package framework.base;

import com.microsoft.playwright.Page;
import framework.factory.PlaywrightFactory;

public class BasePage {

    protected Page page;

    public BasePage() {
        this.page = PlaywrightFactory.getPage();
    }

    public String getTitle() {
        return page.title();
    }

    public String getUrl() {
        return page.url();
    }
}
