package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TaskPage extends PageBase {

    //Mapping
    By actualState = By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[7]/td[1]");
    By updateButton = By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tfoot/tr/td/div/div[1]/form/fieldset/input[3]");

    By stateDropDown = By.id("status");

    By saveUpdateButton = By.xpath("//*[@id=\"update_bug_form\"]/div/div[3]/input");

    //Actions
    public void clickOnUpdateButton(){ click(updateButton); }

    public String getActualState(){
        return driver.findElement(actualState).getText();
    }

    public void updateState(String state) { comboBoxSelectByVisibleText(stateDropDown, state); }

    public void clickOnSaveButton() { click(saveUpdateButton); }
}
