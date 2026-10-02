package framework.factory;

import com.microsoft.playwright.*;
import framework.config.ConfigReader;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void initBrowser() {

        playwright = Playwright.create();

        String browserName = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;

            case "webkit":
                browser = playwright.webkit().launch(options);
                break;

            case "chromium":
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1440, 900)
        );

        page = context.newPage();

        page.setDefaultTimeout(15000);
    }

    public static void startTracing() {
        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    public static void stopTracing(String traceFile) {
        context.tracing().stop(
                new Tracing.StopOptions()
                        .setPath(java.nio.file.Paths.get(traceFile))
        );
    }

    public static void closeBrowser() {

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    public static Page getPage() {
        return page;
    }

    private PlaywrightFactory() {
    }
}
