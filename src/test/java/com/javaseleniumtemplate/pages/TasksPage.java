package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;
public class TasksPage extends PageBase {

    //Mapping
    By attributeToButton = By.id("handler_id_filter");
    By dropDownAttributeTo = By.name("handler_id[]");
    By gravity = By.id("show_severity_filter");
    By dropDownGravity = By.name("severity[]");

    By showQuantity = By.id("per_page_filter");

    By showQuantityTextField = By.name("per_page");

    By saveFilterButton = By.xpath("//*[@id=\"filter\"]/div[2]/div/div/div/div/a[2]");

    By optionsDropdown = By.xpath("//*[@id=\"filter\"]/div[1]/div[1]/div/a");

    By manageOption = By.xpath("//*[@id=\"filter\"]/div[1]/div[1]/div/ul/li[3]/a");

    //Actions
    public void clickOnAttribute(){
        click(attributeToButton);
    }
    public void selectAttributeTo(String username){
        comboBoxSelectByVisibleText(dropDownAttributeTo, username);
    }
    public void clickOnGravity(){
        click(gravity);
    }
    public void selectGravity(String gravity){
        comboBoxSelectByVisibleText(dropDownGravity, gravity);
    }

    public void clickOnQuantity() { click(showQuantity); }

    public void fillShowQuantity(String quantity) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(showQuantityTextField).clear();
        sendKeys(showQuantityTextField, quantity);
    }

    public void clickOnSaveFilter() { click(saveFilterButton); }

    public void clickOnOptionsDropDown() { click(optionsDropdown) ;}

    public void clickOnManageOption() { click(manageOption) ;}
}
