package org.example.frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public record ProductsPage(WebDriver driver) implements HasNavigationBar {

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
