package com.gittigidiyor.test_ui;
import com.gittigidiyor.pages.GittigidiyorPage;
import com.gittigidiyor.utilities.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class GittigidiyorUiTest {
    String url = ConfigReader.getProperty("gittigidiyorUrl");
    String userName = ConfigReader.getProperty("userName");
    String passWord = ConfigReader.getProperty("passWord");
    String loginVerification = "Hesabım";
    String searchText = ConfigReader.getProperty("seachText");
    String secondPageUrl = Driver.getDriver().getCurrentUrl();
    String secondPageVerification = "sf=2";
    Select options;
    String fileName = "productDetails";
    GittigidiyorPage gittigidiyorPage;
    Actions actions;

    @Before
    public void setUp() throws Exception {
        Log4j.startLog("Test  is Starting");
        Driver.getDriver().get(url);
        Log4j.info("Opening Page : " + url);
    }

    @Test
    public void gittiGidiyorTask() {
        //==========Objects============//
        gittigidiyorPage = new GittigidiyorPage();
        actions = new Actions(Driver.getDriver());
        ReusableMethods.waitFor(2);
        ReusableMethods.hover(gittigidiyorPage.signUpIcon);
        ReusableMethods.waitForVisibility(gittigidiyorPage.signUpIcon, 3);
        ReusableMethods.clickWithJS(gittigidiyorPage.signInButton);
        Log4j.info("Sign in Button clicked");
        gittigidiyorPage.userNameTextBox.sendKeys(userName);
        Log4j.info("UserName entered.");
        gittigidiyorPage.passwordTextBox.sendKeys(passWord);
        Log4j.info("Password  entered.");
        ReusableMethods.clickWithJS(gittigidiyorPage.loginButton);
        Log4j.info("Login Button Clicked.");
        Assert.assertTrue(gittigidiyorPage.hesabimText.getText().contains(loginVerification));
        Log4j.info("Login asserted. We logined.");
        gittigidiyorPage.searchTextBox.sendKeys(searchText);
        Log4j.info("Expected word written on the Search textbox");
        gittigidiyorPage.searchButton.click();
        Log4j.info("Search button clicked.");
        ReusableMethods.waitForPageToLoad(6);
        actions.sendKeys(Keys.END).perform();
        Log4j.info("Scroll down to the bottom of the page");
        ReusableMethods.waitFor(3);
        actions.sendKeys(Keys.PAGE_UP).perform();
        Log4j.info("Scroll up to the pages part og Website.");
        ReusableMethods.waitFor(3);
        ReusableMethods.highlight(gittigidiyorPage.secondPage);
        ReusableMethods.waitFor(3);
        gittigidiyorPage.secondPage.click();
        Log4j.info("Navigate to the second page");
        ReusableMethods.waitFor(2);
        Assert.assertTrue(secondPageUrl.contains(secondPageVerification));
        Log4j.info("Second Page asserted.");
        //========WriteToText==========//
        gittigidiyorPage.productLink.click();
        Log4j.info("Clicked the expected product.");
        String productDetails = gittigidiyorPage.productDetails.getText();
        String productCostDetails = gittigidiyorPage.productCostDetails.getText();
        Log4j.info("Product details taken from Website.");
        ReusableMethods.waitForPageToLoad(3);
        WriteToText.write(fileName, productDetails);
        WriteToText.write(fileName, productCostDetails);
        Log4j.info("Write product datas to the Txt file.");
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        gittigidiyorPage.addBasket.click();
        Log4j.info("Product added to the basket.");
        ReusableMethods.waitForPageToLoad(2);
        actions.sendKeys(Keys.UP).perform();
        gittigidiyorPage.myBasketIcon.click();
        ReusableMethods.waitFor(2);
        String productCostDetailsAtBasket = gittigidiyorPage.priceAtBasket.getText();
        Assert.assertTrue(productCostDetails.equals(productCostDetailsAtBasket));
        Log4j.info("Product cost asserted.");
        WebElement productCount=Driver.getDriver().findElement(By.xpath("//select[@class='amount']"));
        options=new Select(productCount);
        options.selectByValue("2");
        Log4j.info("Increased the count of product to 2.");
        ReusableMethods.waitFor(2);
        Assert.assertTrue(gittigidiyorPage.amountOfProduct.equals("Ürün Toplamı (2 Adet)"));
        Log4j.info("Product amount asserted.");
        gittigidiyorPage.deleteProducts.click();
        Log4j.info("All items in the basket have been deleted.");
        ReusableMethods.waitForPageToLoad(3);
        Assert.assertTrue(gittigidiyorPage.amountOfProduct.getText().equals("Ürün Toplamı (0 Adet)"));
        Log4j.info("Asserted that basket is empty.");
    }
    @After
    public void tearDown() throws Exception {
        Log4j.endLog("Test is Ending");
        Driver.closeDriver();
    }
}
