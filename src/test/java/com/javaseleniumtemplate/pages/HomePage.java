package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class HomePage extends PageBase {

    //Mapping
    By createTaskButton = By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[1]/div/a");

    //Actions
    public void clickCreateTask () {
        click(createTaskButton);
    }
}
