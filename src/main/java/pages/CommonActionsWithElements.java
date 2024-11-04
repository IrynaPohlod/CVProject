package pages;


import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWaitLow, webDriverWaitHight;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaitLow = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWaitHight = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT()));

    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try{
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "' was inputted into '" + webElement.getAccessibleName() + "' field");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void enterTextIntoElementWithOptions(WebElement webElement, String suggestionText){
        try{
            webElement.clear();
            webElement.sendKeys(suggestionText);
            By suggestionLocator = By.xpath("//div[contains(@class, 'oxd-autocomplete-option')]/span");
            webDriverWaitLow.until(ExpectedConditions.visibilityOfElementLocated(suggestionLocator));
            List<WebElement> suggestions = webDriver.findElements(suggestionLocator);
            for (WebElement suggestion : suggestions) {
                if (suggestion.getText().equalsIgnoreCase(suggestionText)) {
                    suggestion.click();
                    logger.info("'" + suggestionText + "' was selected from suggestions");
                    return;
                }
            }
            logger.warn("Suggestion '" + suggestionText + "' was not found in the suggestions list.");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try{
            webDriverWaitHight.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = webElement.getAccessibleName();
            webElement.click();
            logger.info("'" + name + "' was clicked");
        }catch(Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info("'" + text + "' was selected in DropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDivDropDown(WebElement dropDown, String text){
                try {
                    dropDown.click();
                    By optionsLocator = By.xpath("//div[@class='oxd-select-option']//span[text()='" + text + "']");
                    WebElement optionToSelect = webDriver.findElement(optionsLocator);
                    optionToSelect.click();
                }catch (Exception e){
                    printErrorAndStopTest(e);
                }
    }


    public boolean isElementDisplayed(WebElement webElement){
        try{
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = "Element is displayed";
            } else {
                message = "Element is Not displayed";
            }
            logger.info(message);
            return state;
        }catch (Exception e){
            logger.info("Element is Not displayed");
            return false;
        }
    }

    public void checkToastMessage(String messageText, WebElement toastMessage){
        try{
            Assert.assertTrue("Toast is not shown", toastMessage.isDisplayed());
            String actualMessage = toastMessage.getText();
            Assert.assertTrue("Incorrect alert message", actualMessage.contains(messageText));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void printErrorAndStopTest (Exception e){
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
    }



}
