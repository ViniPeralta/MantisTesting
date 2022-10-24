package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.pages.CreateTaskPage;
import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTaskTest extends TestBase {
    //Objects
    LoginFlows loginFlow;

    HomePage homePage;

    CreateTaskPage taskPage;

    //Tests
    @Test
    @DisplayName("Test for creating a new task and check if the values are ok when it is created")
    public void createNewTask() throws InterruptedException {

        //Objects instances
        loginFlow = new LoginFlows();
        homePage = new HomePage();
        taskPage = new CreateTaskPage();

        //Parameteres
        String username = "viniciusperalta";
        String password = "123456";
        String optionFrequency = "sempre";
        String optionGravity = "trivial";
        String optionPriority = "nenhuma";
        String resume = "Este é um teste automatizado com Selenium";
        String description = "Este teste está sendo realizado com base no que foi aprendido nas aulas com a Base2";

        //Test
        loginFlow.efetuarLogin(username, password);

        homePage.clickCreateTask();

        taskPage.selectFrequencyOption(optionFrequency);
        taskPage.selectGravityOption(optionGravity);
        taskPage.selectPriorityOption(optionPriority);
        taskPage.selectAtributeOption(username);
        taskPage.fillResumeTextField(resume);
        taskPage.fillDescriptionTextField(description);
        taskPage.clickCreateTaskButton();

        Thread.sleep(5000);

        assertAll(
                () -> assertEquals("público", taskPage.getVisibility()),
                () -> assertEquals(username, taskPage.getAtribute()),
                () -> assertEquals(optionPriority, taskPage.getPriority()),
                () -> assertEquals(optionGravity, taskPage.getGravity()),
                () -> assertEquals(optionFrequency, taskPage.getFrequency()),
                () -> assertEquals(taskPage.getNum() + ": " + resume, taskPage.getResume()),
                () -> assertEquals(description, taskPage.getDescription())
        );
    }
}
