package com.javaseleniumtemplate.pages;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class LoginPage extends PageBase {
    //Mapping
    By usernameField = By.id("username");
    By enterUsername = By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]");

    By passwordField = By.id("password");

    By enterPassword = By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]");

    //Actions
    public void preenhcerUsuario(String usuario){
        sendKeys(usernameField, usuario);
    }
    public void preencherSenha(String senha){
        sendKeys(passwordField, senha);
    }
    public void clicarEmEntrarUsuario() {
        click(enterUsername);
    }

    public void clicarEmEntrarSenha(){
        click(enterPassword);
    }
}
