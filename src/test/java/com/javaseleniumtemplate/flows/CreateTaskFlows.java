package com.javaseleniumtemplate.flows;

import com.javaseleniumtemplate.pages.CreateTaskPage;
import com.javaseleniumtemplate.pages.HomePage;

public class CreateTaskFlows {
    //Objects and constructor
    HomePage homePage;
    CreateTaskPage taskPage;
    public CreateTaskFlows(){
        //Page and Steps Objects
        homePage = new HomePage();
        taskPage = new CreateTaskPage();
    }

    //Flows
    public void createTask(String frequency, String gravity, String priority, String username,
    String resume, String description){
        homePage.clickCreateTask();
        taskPage.selectFrequencyOption(frequency);
        taskPage.selectGravityOption(gravity);
        taskPage.selectPriorityOption(priority);
        taskPage.selectAtributeOption(username);
        taskPage.fillResumeTextField(resume);
        taskPage.fillDescriptionTextField(description);
        taskPage.clickCreateTaskButton();
    }
}
