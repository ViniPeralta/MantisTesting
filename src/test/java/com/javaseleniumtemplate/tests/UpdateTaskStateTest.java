package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.TaskPage;
import com.javaseleniumtemplate.pages.TasksPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTaskStateTest extends TestBase {
    //Objects
    LoginFlows loginFlow;

    HomePage homePage;

    TasksPage tasksPage;

    TaskPage taskPage;

    //Tests
    @Test
    @DisplayName("Test for updating the taskState")
    public void updateTaskState() {

        //Objects instances
        loginFlow = new LoginFlows();
        homePage = new HomePage();
        tasksPage = new TasksPage();
        taskPage = new TaskPage();

        //Parameters
        String username = "viniciusperalta";
        String password = "123456";
        String updateTask = "0000062";

        //Test
        loginFlow.login(username, password);

        homePage.clickViewTasks();

        List<WebElement> rows = tasksPage.getTasksRows();

        for(int i = 1; i <= rows.size(); i++){
            String task = tasksPage.getTaskNum(String.valueOf(i));

            if(Objects.equals(updateTask, task)){
                tasksPage.openTaskToBeUpdated(String.valueOf(i));

                break;
            }
        }

        String actualState = taskPage.getActualState();

        taskPage.clickOnUpdateButton();

        String state = "";

        if(actualState.equals("resolvido")){
            taskPage.updateState("retorno");
            state = "retorno";
        } else {
            taskPage.updateState("resolvido");
            state = "resolvido";
        }

        taskPage.clickOnSaveButton();

        homePage.clickViewTasks();

        rows = tasksPage.getTasksRows();

        String updatedState = "";

        for(int i = 1; i <= rows.size(); i++){
            String task = tasksPage.getTaskNum(String.valueOf(i));

            if(Objects.equals(updateTask, task)){
                updatedState = tasksPage.getTaskState(String.valueOf(i));

                break;
            }
        }

        Assertions.assertEquals(state, updatedState);
    }
}
