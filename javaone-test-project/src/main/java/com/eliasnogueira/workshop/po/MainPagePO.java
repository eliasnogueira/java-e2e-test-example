package com.eliasnogueira.workshop.po;

import com.eliasnogueira.workshop.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPagePO {

    @FindBy(id = "adicionar")
    WebElement addButton;
    @FindBy(id = "pesquisar")
    WebElement searchInput;
    @FindBy (id = "editar")
    WebElement editButton;
    @FindBy(id = "remover")
    WebElement deleteButton;
    @FindBy(id = "nome")
    WebElement nameFromGrid;
    @FindBy(id = "endereco")
    WebElement addressFromGrid;
    @FindBy(id = "hobbies")
    WebElement hobbiesFromGrid;
    private WebDriver driver;

    public MainPagePO(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        driver.get(Utils.getValueFromConf("base.url"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("editar")));
    }

    public void clickOnAdd() {
        addButton.click();
    }

    public void search(String term) {
        searchInput.sendKeys(term);
    }

    public void clickOnEdit() {
        editButton.click();
    }

    public void clickOnDelete() {
        deleteButton.click();
    }

    public String pageContent() {
        return driver.getPageSource();
    }

    public String getNameFromList() {
        return nameFromGrid.getText();
    }

    public String getAddressFromList() {
        return addressFromGrid.getText();
    }

    public String getHobbiesFromList() {
        return hobbiesFromGrid.getText();
    }
}
