package tests;

import com.microsoft.playwright.Page;
import framework.config.ConfigReader;
import framework.factory.PlaywrightFactory;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private Page page;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        PlaywrightFactory.initBrowser();
        PlaywrightFactory.startTracing();

        page = PlaywrightFactory.getPage();
        homePage = new HomePage(page);
        loginPage = new LoginPage(page);

        page.navigate(ConfigReader.get("baseUrl"));
    }

    @Test
    public void verifyHomePage() {
        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "Home page was not displayed."
        );
    }

    @Test
    public void verifyLoginPage() {
        homePage.openLoginPage();

        Assert.assertTrue(
                page.locator("#Email").isVisible(),
                "Email field was not displayed."
        );

        Assert.assertTrue(
                page.locator("#Password").isVisible(),
                "Password field was not displayed."
        );

        Assert.assertTrue(
                page.locator("input[value='Log in']").isVisible(),
                "Login button was not displayed."
        );
    }

    
//     after putting a valid Demo Web Shop account

     
     @Test
     public void verifyValidLogin() {
    	 System.out.println("username "+ ConfigReader.get("username")+"passwor: "+ConfigReader.get("password"));
       homePage.openLoginPage();
       loginPage.login(
          ConfigReader.get("username"),
              ConfigReader.get("password")
          );
     
          Assert.assertTrue(
              loginPage.isLogoutDisplayed(),
              "Login failed."
          );
         
      }
     

    @AfterClass
    public void tearDown() {
        PlaywrightFactory.stopTracing("test-output/trace.zip");
        PlaywrightFactory.closeBrowser();
    }
}
