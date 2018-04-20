package com.eliasnogueira.workshop.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewEditPagePO {

    @FindBy(id = "nome")
    WebElement nameInput;
    @FindBy(name = "endereco")
    WebElement addressInput;
    @FindBy(id = "hobbies")
    WebElement hobbiesInput;
    @FindBy(id = "voltar")
    WebElement backButton;
    @FindBy(css = ".w3-btn.w3-teal")
    WebElement saveButton;
    private WebDriver driver;

    public NewEditPagePO(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("voltar")));
    }

    public void fillName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void fillAddress(String address) {
        addressInput.clear();
        addressInput.sendKeys(address);
    }

    public void fillHobbies(String hobbies) {
        hobbiesInput.clear();
        hobbiesInput.sendKeys(hobbies);
    }

    public void clickOnBack() {
        backButton.click();
    }

    public void clickOnSave() {
        saveButton.click();
    }
}
