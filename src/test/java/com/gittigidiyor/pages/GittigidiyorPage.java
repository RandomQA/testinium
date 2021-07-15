package com.gittigidiyor.pages;

import com.gittigidiyor.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GittigidiyorPage {
    public GittigidiyorPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='gekhq4-6 hnYHyk']")
    public WebElement signUpIcon;

    @FindBy(xpath = "//div[@class='sc-3wdx43-0 bAxXdY']//div//div//div//a")
    public WebElement signInButton;

    @FindBy(id = "L-UserNameField")
    public WebElement userNameTextBox;

    @FindBy(xpath = "//input[@id='L-PasswordField']")
    public WebElement passwordTextBox;

    @FindBy(xpath = "//input[@class='gg-m-24 gg-t-24 gg-d-24 gg-w-24 gg-ui-btn-primary gg-ui-btn-fluid  gg-ui-btn-lg']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class='gekhq4-4 egoSnI']")
    public WebElement hesabimText;

    @FindBy(xpath = "//input[@data-cy='header-search-input']")
    public WebElement searchTextBox;

    @FindBy(xpath = "//button[@class='qjixn8-0 sc-1bydi5r-0 gaMakD']")
    public WebElement searchButton;

    @FindBy(xpath = "//a[@href='/arama/?k=bilgisayar&sf=2']")
    public WebElement secondPage;

    @FindBy(xpath = "(//p[@class='image-container product-hslider-container'])[1]")
    public WebElement productLink;

    @FindBy(xpath = "//h1[@class='title r-onepp-title']")
    public WebElement productDetails;

    @FindBy(xpath = "//div[@id='sp-price-lowPrice']")
    public WebElement productCostDetails;

    @FindBy(xpath = "//button[@id='add-to-basket']")
    public WebElement addBasket;

    @FindBy(xpath = "//div[@class='icon-sepet-line-wrapper']")
    public WebElement myBasketIcon;

    @FindBy(xpath = "//p[@class='new-price']")
    public WebElement priceAtBasket;

    @FindBy(xpath = "//select[@class='amount']")
    public WebElement dropdown;

    @FindBy(xpath = "//div[@class='gg-d-16 detail-text']")
    public WebElement amountOfProduct;

    @FindBy(xpath = "(//i[@class='gg-icon gg-icon-bin-medium'])[2]")
    public WebElement deleteProducts;







}
