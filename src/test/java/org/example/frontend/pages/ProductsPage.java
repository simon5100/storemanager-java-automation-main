package org.example.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(., 'Личный кабинет')]")
    WebElement privateAccount;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean isPrivateAccountDisplayed() {
        return privateAccount.isDisplayed();
    }
}
