package com.javaseleniumtemplate.tests;

import com.javaseleniumtemplate.bases.TestBase;
import com.javaseleniumtemplate.flows.DevStatsFlow;
import com.javaseleniumtemplate.flows.LoginFlows;
import com.javaseleniumtemplate.flows.UpdateTaskStateFlows;
import com.javaseleniumtemplate.pages.FilterPage;
import com.javaseleniumtemplate.pages.HomePage;
import com.javaseleniumtemplate.pages.ResumePage;
import com.javaseleniumtemplate.pages.TasksPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;

public class DevStatsTest extends TestBase {

    private static final DecimalFormat df = new DecimalFormat("0.0");

    //Objects
    LoginFlows loginFlow;

    HomePage homePage;

    TasksPage tasksPage;

    ResumePage resumePage;

    UpdateTaskStateFlows updateTaskStateFlows;

    DevStatsFlow devStatsFlow;

    //Tests
    @Test
    @DisplayName("Test for updating a task (resolved - return) and checking if the DevStatsResume fields are correct")
    public void checkDevStatsResumeFields() {

        //Objects instances
        loginFlow = new LoginFlows();
        homePage = new HomePage();
        tasksPage = new TasksPage();
        resumePage = new ResumePage();
        updateTaskStateFlows = new UpdateTaskStateFlows();
        devStatsFlow = new DevStatsFlow();

        //Parameters
        String username = "viniciusperalta";
        String password = "123456";
        String taskSelected = "0000062";
        String initialStatus = "";
        long initialFinishedTasks;

        //Test
        loginFlow.login(username, password);

        homePage.clickViewTasks();

        List<WebElement> taskRows = tasksPage.getTasksRows();

        for(int i = 1; i <= taskRows.size(); i++){
            String task = tasksPage.getTaskNum(String.valueOf(i));

            if(Objects.equals(taskSelected, task)){
                initialStatus = tasksPage.getTaskState(String.valueOf(i));

                break;
            }
        }

        homePage.clickViewResume();

        List<WebElement> devStatsRows = resumePage.getDevStatsRows();

        initialFinishedTasks = devStatsFlow.getFinishedTasks(devStatsRows.size(), username);

        updateTaskStateFlows.updateTaskState(taskSelected);

        homePage.clickViewResume();

        //Values to assert
        long updatedFinishedTasks = initialStatus.equals("resolvido") ?
                initialFinishedTasks - 1 :
                initialFinishedTasks + 1;

        long actualFinishedTasks = devStatsFlow.getFinishedTasks(devStatsRows.size(), username);

        double totalTasks = devStatsFlow.getTotalTasks(devStatsRows.size());
        double totalTasksByUser = devStatsFlow.getTotalTasksByUser(username, devStatsRows.size());

        String userProportion = df.format((totalTasksByUser / totalTasks) * 100);
        String actualUserProportion = devStatsFlow.getUserProportion(username, devStatsRows.size());

        String resolvedProportion = df.format((updatedFinishedTasks / totalTasksByUser) * 100);
        String actualResolvedProportion = devStatsFlow.getUserResolvedProportion(username, devStatsRows.size());

        assertAll(
                () -> Assertions.assertEquals(updatedFinishedTasks, actualFinishedTasks),
                () -> Assertions.assertEquals(userProportion + "%", actualUserProportion),
                () -> Assertions.assertEquals(resolvedProportion + "%", actualResolvedProportion)
        );
    }
}
