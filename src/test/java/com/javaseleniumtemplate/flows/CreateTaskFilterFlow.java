package com.javaseleniumtemplate.flows;

import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.TasksPage;

public class CreateTaskFilterFlow {
    //Objects and constructor
    HomePage homePage;
    TasksPage tasksPage;
    public CreateTaskFilterFlow(){
        //Page and Steps Objects
        homePage = new HomePage();
        tasksPage = new TasksPage();
    }

    //Flows
    public void createTaskFilter(String username, String gravity, String quantity) throws InterruptedException {
        homePage.clickViewTasks();

        tasksPage.clickOnAttribute();
        tasksPage.selectAttributeTo(username);
        tasksPage.clickOnGravity();
        tasksPage.selectGravity(gravity);
        tasksPage.clickOnQuantity();
        tasksPage.fillShowQuantity(quantity);
        tasksPage.clickOnSaveFilter();

    }
}
