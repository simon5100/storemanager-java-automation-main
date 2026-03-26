package org.example.frontend.pages.elements;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.backend.models.SupplierCreateModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@NoArgsConstructor
@AllArgsConstructor
public class SuppliersTableRow {

    WebElement root;

    @Step("Get SupplierTableRow name")
    public String getName() {
        return root.findElement(By.xpath(".//td[1]")).getText();
    }

    @Step("Get SupplierTableRow model")
    public SupplierCreateModel getSupplierCreateModel() {
        return SupplierCreateModel.builder()
                .name(root.findElement(By.xpath(".//td[1]")).getText())
                .contactName(root.findElement(By.xpath(".//td[2]")).getText())
                .contactEmail(root.findElement(By.xpath(".//td[3]")).getText())
                .phoneNumber(root.findElement(By.xpath(".//td[4]")).getText())
                .address(root.findElement(By.xpath(".//td[5]")).getText())
                .country(root.findElement(By.xpath(".//td[6]")).getText())
                .city(root.findElement(By.xpath(".//td[7]")).getText())
                .website(root.findElement(By.xpath(".//td[8]")).getText())
                .build();
    }

    @Step("Click edit button for SuppliersTableRow")
    public void clickEditButton() {
        root.findElement(By.cssSelector("button.btn-outline-warning")).click();
    }

    @Step("Click delete button for SuppliersTableRow")
    public void clickDeleteButton() {
        root.findElement(By.cssSelector("button.btn-outline-danger")).click();
    }
}
