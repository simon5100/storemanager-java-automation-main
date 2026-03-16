package org.example.frontend.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class SuppliersPages implements HasNavigationBar {

    private final WebDriver driver;

    public SuppliersPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
