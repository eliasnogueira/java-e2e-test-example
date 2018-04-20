package com.eliasnogueira.workshop.web.po;

import static org.testng.Assert.*;

import com.eliasnogueira.workshop.po.MainPagePO;
import com.eliasnogueira.workshop.po.NewEditPagePO;
import com.eliasnogueira.workshop.utils.BaseTest;
import org.testng.annotations.Test;

public class AlterPersonTest extends BaseTest {

    @Test
    public void alterPerson_Successfully() {
        MainPagePO mainPage = new MainPagePO(driver);
        mainPage.search("Bruno Borges");

        assertEquals(mainPage.getNameFromList(), "Bruno Borges");
        assertEquals(mainPage.getAddressFromList(), "Vancouver");
        assertEquals(mainPage.getHobbiesFromList(), "Java advocacy");

        mainPage.clickOnEdit();

        NewEditPagePO edit = new NewEditPagePO(driver);
        edit.fillHobbies("Java forever <3");
        edit.clickOnSave();


    }
}
