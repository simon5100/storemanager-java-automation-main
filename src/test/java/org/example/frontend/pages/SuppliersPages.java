package org.example.frontend.pages;

import lombok.Getter;
import org.example.frontend.pages.elements.SuppliersTableRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

@Getter
public class SuppliersPages implements HasNavigationBar {

    private final WebDriver driver;

    @FindBy(css = "table#suppliers-table tbody tr")
    List<WebElement> suppliersTableRows;

    @FindBy(xpath = "//*[contains(text(), 'Поставщик успешно удален')]")
    WebElement deletedSupplierNotification;


    public SuppliersPages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public SuppliersTableRow getTableRowByName(String name) {
        return suppliersTableRows.stream()
                .map(SuppliersTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    public  boolean isDeletedSupplierNotificationDisplayed() {
        return deletedSupplierNotification.isDisplayed();
    }

    public boolean isDeletedSupplierExistsOnThePage(String name) {

        return suppliersTableRows.stream()
                .map(SuppliersTableRow::new)
                .map(SuppliersTableRow::getName)
                .toList().contains(name);

    }
}
