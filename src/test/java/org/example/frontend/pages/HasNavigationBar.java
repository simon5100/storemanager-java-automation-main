package org.example.frontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface HasNavigationBar {

    WebDriver driver();

    default boolean isPrivateAccountDisplayed() {

        return driver().findElement(By.xpath("//nav//a[contains(., 'Личный кабинет')]")).isDisplayed();
    }

    default void clickProductsLink() {
        driver().findElement(By.xpath("//nav//a[contains(., 'Продукты')]")).click();
    }

    default void clickSuppliersLink() {
        driver().findElement(By.xpath("//nav//a[contains(., 'Поставщики')]")).click();
    }

    default void clickWarehousesLink() {
        driver().findElement(By.xpath("//nav//a[contains(., 'Склады')]")).click();
    }
}
