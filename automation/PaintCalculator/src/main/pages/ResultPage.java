package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import main.utility.BasePage;

public class ResultPage extends BasePage {

	public ResultPage(WebDriver aDriver) {
		super(aDriver);
		PageFactory.initElements(driver, this);	
		
		// Check that we're on the right page.
		if(!isDisplayed()){
			Assert.fail("Results Page is not displayed");			
		}			
	}

    // The page contains several HTML elements that will be represented as WebElements.
    // The locators for these elements should only be defined once.		
	
	@FindBy(how = How.XPATH, using = "//input[@value='Home']")
    private WebElement homeBtn;
	
	private String pageTitle = "Results!";
	
    /*************************************************************************
     * Method description: 
     * Verify if the page is displayed
     *************************************************************************/
	public boolean isDisplayed(){
		
		boolean valueToReturn = false;
		
		if(driver.getTitle().equals(pageTitle)){
			System.out.println("Results Page is displayed");
			valueToReturn = true;
		}else{
			System.out.println("Results Page is notdisplayed");
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
     * Get Amount Of Feet To Paint text for particular room
     *************************************************************************/
	public int getAmountOfFeetToPaint(int room){
		
		return Integer.parseInt(getElementText(By.xpath("//tr[" + (room +1) +"]/td[@class='ft']"))); 
	}
	
    /*************************************************************************
     * Method description: 
     * Get gallon required text for particular room
     *************************************************************************/
	public int getGallonsRequired(int room){
		
		return Integer.parseInt(getElementText(By.xpath("//tr[" + (room +1) +"]/td[@class='gallons']"))); 
	}
	
    /*************************************************************************
     * Method description: 
     * Get total gallon required text
     *************************************************************************/
	public int getTotalGallonsRequired(){
		
		String text = getElementText(By.xpath("//h5[starts-with(text(),'Total Gallons Required')]"));
		String[] textArr = text.split(": ");
		return Integer.parseInt(textArr[1]); 
	}

    /*************************************************************************
     * Method description: 
     * Click on Home button
     *************************************************************************/
	public void clickHomeBtn(){
		
		System.out.println("Click on Home button");
		clickOn(homeBtn);
		
	}
}
