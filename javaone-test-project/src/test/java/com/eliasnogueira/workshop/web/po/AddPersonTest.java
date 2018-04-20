package com.eliasnogueira.workshop.web.po;

import static org.testng.Assert.*;

import com.eliasnogueira.workshop.po.MainPagePO;
import com.eliasnogueira.workshop.po.NewEditPagePO;
import com.eliasnogueira.workshop.utils.BaseTest;
import org.testng.annotations.Test;

public class AddPersonTest extends BaseTest {

    private String name = "Bruno Borges";
    private String address = "Vancouver";
    private String hobbies = "Java advocacy";

    @Test
    public void addPerson_successfully() {
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.clickOnAdd();

        NewEditPagePO newPerson = new NewEditPagePO(driver);
        newPerson.fillName(name);
        newPerson.fillAddress(address);
        newPerson.fillHobbies(hobbies);
        newPerson.clickOnSave();

        mainPage = new MainPagePO(driver);
        String content = mainPage.pageContent();

        assertTrue(content.contains(name), name + " is not present");
        assertTrue(content.contains(address), address + " is not present");
        assertTrue(content.contains(hobbies), hobbies + " is not present");

    }

}
