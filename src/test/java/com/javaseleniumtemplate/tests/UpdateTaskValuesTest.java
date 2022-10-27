package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.flows.CreateTaskFlows;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.TaskPage;
import com.javaseleniumtemplate.pages.TasksPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;

public class UpdateTaskValuesTest extends TestBase {

    //Objects
    LoginFlows loginFlow;
    HomePage homePage;
    CreateTaskFlows createTaskFlows;

    TasksPage tasksPage;

    TaskPage taskPage;

    //Tests
    @Test
    @DisplayName("Test for updating a task and checking if the values are correctly saved")
    public void updateTaskValuesTest() {

        //Objects instances
        loginFlow = new LoginFlows();
        homePage = new HomePage();
        tasksPage = new TasksPage();
        taskPage = new TaskPage();
        createTaskFlows = new CreateTaskFlows();

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

        String initialResume = taskPage.getResumeText();
        String initialDescription = taskPage.getDescriptionText();

        taskPage.clickOnUpdateButton();

        String updatedResume;

        if(initialResume.equals(updateTask + ": Este é um teste automatizado com Selenium")){
            updatedResume = "Será que isso foi automatizado?";
        } else {
            updatedResume = "Este é um teste automatizado com Selenium";
        }

        String updatedDescription;

        if(initialDescription.equals("Este teste está sendo realizado com base no que foi aprendido nas aulas com a Base2")){
            updatedDescription = "Descrição em manutenção!!";
        } else {
            updatedDescription = "Este teste está sendo realizado com base no que foi aprendido nas aulas com a Base2";
        }

        taskPage.fillResumeTextField(updatedResume);
        taskPage.fillDescriptionTextField(updatedDescription);

        taskPage.clickOnSaveButton();

        homePage.clickViewTasks();

        rows = tasksPage.getTasksRows();

        for(int i = 1; i <= rows.size(); i++){
            String task = tasksPage.getTaskNum(String.valueOf(i));

            if(Objects.equals(updateTask, task)){
                tasksPage.openTaskToBeUpdated(String.valueOf(i));

                break;
            }
        }

        assertAll(
                () -> Assertions.assertEquals(updateTask + ": " + updatedResume, taskPage.getResumeText()),
                () -> Assertions.assertEquals(updatedDescription, taskPage.getDescriptionText())
        );
    }
}
