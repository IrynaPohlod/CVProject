package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract class ParentPage extends CommonActionsWithElements{

    protected String baseUrl;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseUrl = configProperties.base_url().replace("[env]", System.getProperty("env","demo"));
    }

    abstract String getRelativeUrl();

    @Step
    protected void checkUrl(){
        Assert.assertEquals("Invalid page",baseUrl + getRelativeUrl(),webDriver.getCurrentUrl());
    }



}
