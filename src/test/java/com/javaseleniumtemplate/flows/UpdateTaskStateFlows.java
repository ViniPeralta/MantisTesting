package com.javaseleniumtemplate.flows;

import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.LoginPage;
import com.javaseleniumtemplate.pages.TaskPage;
import com.javaseleniumtemplate.pages.TasksPage;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class UpdateTaskStateFlows {
    //Objects and constructor
    HomePage homePage;
    TasksPage tasksPage;
    TaskPage taskPage;

    public UpdateTaskStateFlows(){
        //Page and Steps Objects
        homePage = new HomePage();
        tasksPage = new TasksPage();
        taskPage = new TaskPage();
    }

    //Flows
    public void updateTaskState(String updateTask){
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

        if(actualState.equals("resolvido")){
            taskPage.updateState("retorno");
        } else {
            taskPage.updateState("resolvido");
        }

        taskPage.clickOnSaveButton();
    }
}
