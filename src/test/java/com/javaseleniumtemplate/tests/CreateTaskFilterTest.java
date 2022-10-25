package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.FilterPage;
import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.TasksPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTaskFilterTest extends TestBase {
    //Objects
    LoginFlows loginFlow;
    HomePage homePage;

    TasksPage tasksPage;

    FilterPage filterPage;

    //Tests
    @Test
    @DisplayName("Test for creating a new filter, checking if it has been saved correctly")
    public void createNewFilter() throws InterruptedException {

        //Objects instances
        loginFlow = new LoginFlows();
        homePage = new HomePage();
        tasksPage = new TasksPage();
        filterPage = new FilterPage();

        //Parameters
        String username = "viniciusperalta";
        String password = "123456";
        String gravity = "trivial";
        String quantity = "10";
        String name = "testePeralta";

        //Test
        loginFlow.login(username, password);

        homePage.clickViewTasks();

        tasksPage.clickOnOptionsDropDown();
        tasksPage.clickOnManageOption();

        List<WebElement> filterTableRows = filterPage.findTableRows();

        for(int i = 1; i < filterTableRows.size(); i++){

            String filter = filterPage.getFilterName(String.valueOf(i));

            if(Objects.equals(filter, name)){
                filterPage.deleteRepeatedFilter(String.valueOf(i));

                filterPage.clickOnDeleteButton();

                break;
            }
        }

        homePage.clickViewTasks();

        tasksPage.clickOnAttribute();
        tasksPage.selectAttributeTo(username);
        tasksPage.clickOnGravity();
        tasksPage.selectGravity(gravity);
        tasksPage.clickOnQuantity();
        tasksPage.fillShowQuantity(quantity);
        tasksPage.clickOnSaveFilter();

        filterPage.fillFilterName(name);
        filterPage.checkAllProjects();
        filterPage.clickOnSaveFilter();

        tasksPage.clickOnOptionsDropDown();
        tasksPage.clickOnManageOption();

        filterTableRows = filterPage.findTableRows();

        for(int i = 1; i < filterTableRows.size(); i++){

            String filter = filterPage.getFilterName(String.valueOf(i));

            if(Objects.equals(filter, name)){
                filterPage.clickOnNewFilter(String.valueOf(i));

                break;
            }
        }

        String filterAttributedTo = filterPage.getAttributeToValue();
        String filterGravity = filterPage.getGravity();
        String filterShowValue = filterPage.getShowValue();

        //The filter is not being saved correctly, so the test is failing - Bug Report :O
        assertAll(
                () -> Assertions.assertEquals(username, filterAttributedTo),
                () -> Assertions.assertEquals(gravity, filterGravity),
                () -> Assertions.assertEquals(quantity, filterShowValue)
        );
    }
}
