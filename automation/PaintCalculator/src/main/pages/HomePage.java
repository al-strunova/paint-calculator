package main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import main.utility.BasePage;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver aDriver) {
		super(aDriver);
		PageFactory.initElements(driver, this);	
		
		// Check that we're on the right page.
		if(!isDisplayed()){
			Assert.fail("Home Page is not displayed");			
		}			
	}

    // The page contains several HTML elements that will be represented as WebElements.
    // The locators for these elements should only be defined once.			
	
	@FindBy(how = How.NAME, using = "rooms")
    private WebElement nbrOfRoomsField;
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submitBtn;
	
	private String pageTitle = "Calculating Paint Amount";

    /*************************************************************************
     * Method description: 
     * Verify if the page is displayed
     *************************************************************************/
	public boolean isDisplayed(){
		
		boolean valueToReturn = false;
		
		if(driver.getTitle().equals(pageTitle)){
			System.out.println("Home Page is displayed");
			valueToReturn = true;
		}else{
			System.out.println("Home Page is not displayed");
		}
		
		return valueToReturn;
	}
	
    /*************************************************************************
     * Method description: 
     * Set value in number of rooms field
     *************************************************************************/
	public void setValueToNbrOfRoomsField(int nbrOfRooms){
		
		System.out.println("Set >" + nbrOfRooms + "< as a number of rooms.");
		sendKeysTo(nbrOfRoomsField, Integer.toString(nbrOfRooms));
		
	}

    /*************************************************************************
     * Method description: 
     * Click on Submit button
     *************************************************************************/
	public void clickOnSubmitBtn(){
		
		System.out.println("Click on Submit button");
		clickOn(submitBtn);
		
	}

    /*************************************************************************
     * Method description: 
     * Verify if the number of room field is displayed
     *************************************************************************/
	public boolean isNbrOfRoomFieldDisplayed() {
		boolean valueToReturn = false;
		
		if(isElementPresentAndDisplay(nbrOfRoomsField)){
			System.out.println("'Number of rooms' field is displayed");
			valueToReturn = true;
		}else{
			System.out.println("'Number of rooms' field is not displayed");
		}
		
		return valueToReturn;
	}

    /*************************************************************************
     * Method description: 
     * Verify if submit button is displayed
     *************************************************************************/
	public boolean isSubmitBtnDisplayed() {
		boolean valueToReturn = false;
		
		if(isElementPresentAndDisplay(submitBtn)){
			System.out.println("'Submit' button is displayed");
			valueToReturn = true;
		}else{
			System.out.println("'Submit' button is not displayed");
		}
		
		return valueToReturn;
	}

}
