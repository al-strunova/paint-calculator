package main.pages;

import main.utility.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DimensionsPage extends BasePage {

	public DimensionsPage(WebDriver aDriver) {
		super(aDriver);
		PageFactory.initElements(driver, this);	
		
		// Check that we're on the right page.
		if(!isDisplayed()){
			Assert.fail("Dimensions Page is not displayed");			
		}			
	}

    // The page contains several HTML elements that will be represented as WebElements.
    // The locators for these elements should only be defined once.		
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submitBtn;
	
	private String pageTitle = "Dimension Calculation";

    /*************************************************************************
     * Method description: 
     * Verify if the page is displayed
     *************************************************************************/
	public boolean isDisplayed(){
		
		boolean valueToReturn = false;
		
		if(driver.getTitle().equals(pageTitle)){
			System.out.println("Dimensions Page is displayed");
			valueToReturn = true;
		}else{
			System.out.println("Dimensions Page is notdisplayed");
		}
		
		return valueToReturn;
	}
	
    /*************************************************************************
     * Method description: 
     * Get Number of rows
     *************************************************************************/
	public int getNbrOfRoomRows(){		
		return getElements(By.xpath("//tr/td[1]")).size(); 
	}
	
    /*************************************************************************
     * Method description: 
     * Set value in Length field
     *************************************************************************/
	public void setValueToLengthField(int length, int room){
		
		System.out.println("Set >" + length + "< value to 'Length' to >" + room + "< room. ");
		sendKeysTo(By.name("length-" + (room-1)), Integer.toString(length));
		
	}
	
    /*************************************************************************
     * Method description: 
     * Verify if Width field is displayed
     *************************************************************************/
	public boolean isLengthFieldDisplayed(int room) {
		boolean valueToReturn = false;
		
		if(isElementPresentAndDisplay(By.name("length-" + (room-1)))){
			System.out.println("'Length' field is displayed for >" + room + "< room. ");
			valueToReturn = true;
		}else{
			System.out.println("'Length' field is not displayed for >" + room + "< room. ");
		}
		
		return valueToReturn;
	}
	
    /*************************************************************************
     * Method description: 
     * Set value in Width field
     *************************************************************************/
	public void setValueToWidthField(int width, int room){
		
		System.out.println("Set >" + width + "< value to 'Width' to >" + room + "< room. ");
		sendKeysTo(By.name("width-" + (room-1)), Integer.toString(width));
		
	}

    /*************************************************************************
     * Method description: 
     * Verify if Width field is displayed
     *************************************************************************/
	public boolean isWidthFieldDisplayed(int room) {
		boolean valueToReturn = false;
		
		if(isElementPresentAndDisplay(By.name("width-" + (room-1)))){
			System.out.println("'Width' field is displayed for >" + room + "< room. ");
			valueToReturn = true;
		}else{
			System.out.println("'Width' field is not displayed for >" + room + "< room. ");
		}
		
		return valueToReturn;
	}
	
    /*************************************************************************
     * Method description: 
     * Set value in Height field
     *************************************************************************/
	public void setValueToHeightField(int height, int room){
		
		System.out.println("Set >" + height + "< value to 'Height' to >" + room + "< row.");
		sendKeysTo(By.name("height-" + (room-1)), Integer.toString(height));
		
	}
	
    /*************************************************************************
     * Method description: 
     * Verify if Height field is displayed
     *************************************************************************/
	public boolean isHeightFieldDisplayed(int room) {
		boolean valueToReturn = false;
		
		if(isElementPresentAndDisplay(By.name("height-" + (room-1)))){
			System.out.println("'Height' field is displayed for >" + room + "< row.");
			valueToReturn = true;
		}else{
			System.out.println("'Height' field is not displayed for >" + room + "< row.");
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


    /*************************************************************************
     * Method description: 
     * Click on Submit button
     *************************************************************************/
	public void clickOnSubmitBtn(){
		
		System.out.println("Click on Submit button");
		clickOn(submitBtn);
		
	}
}
