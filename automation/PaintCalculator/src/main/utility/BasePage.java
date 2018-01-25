package main.utility;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/************************************************************************
 * Class description:  
 * It provides common methods for all classes/pages in current framework.  
 *************************************************************************/

public class BasePage {

	  protected WebDriver driver; 
	  protected WebDriverWait wait;
	  protected String driverName;
	  private static final int DEFAULT_TIMEOUT = 30;

	  public BasePage(WebDriver aDriver) {
		  driver = aDriver;
		  wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
		  driverName = Driver.getBrowserName().toUpperCase();
		  
	  }

    /*************************************************************************
     * Method description: 
     * Returns the first WebElement using the given method.  	   
	 * It shortens "driver.findElement(By)".
     *************************************************************************/ 
	public WebElement getElement(By by){
		  
		try{
			WebElement element = driver.findElement(by);
			return element;
		}catch(Exception e){
			System.out.println("FAILED to return web element");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("FAILED to return web element. Exception description : " + e.getMessage());
			return null;
		}
		  
	}	

    /*************************************************************************
     * Method description: 
     * Wait for element, report failure if needed
     *************************************************************************/ 
	public WebElement waitForElement(WebElement element){
		
		try{
			element = wait.until(ExpectedConditions.visibilityOf(element));	
		}catch(Exception e){
			System.out.println("Failed to wait for element");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("Failed to wait for element. Exception description : " + e.getMessage());	
		}
		
		return element;
		  
	}
	
    /*************************************************************************
     * Method description: 
     * Try to click on element, report failure if needed
     *************************************************************************/ 
	public void clickOn(WebElement element){
		  
		try{
			waitForElement(element);
			wait.until(new tryToClickOnElement(element));	
		}catch(Exception e){
			System.out.println("Failed to click on element");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("Failed to click on element. Exception description : " + e.getMessage());	
		}
		  
	}
		
	/*************************************************************************
     * Method description: 
     * Returns the Web Element Value.  	   
	 * It shortens "driver.findElement(By).getText()".
     *************************************************************************/ 
	public String getElementText(By findElementBy){
		  
		try{
			String elementText = getElement(findElementBy).getText();
			return elementText;
		}catch(Exception e){
			System.out.println("FAILED to return web element text");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("FAILED to return web element text. Exception description : " + e.getMessage());
			return null;
		}
	}
			  
    /*************************************************************************
     * Method description: 
     * Returns a list of  WebElements using the given method.  	   
	 * It shortens "driver.findElements(By)".
     *************************************************************************/ 
	public List<WebElement> getElements(By by){
		  
		try{
			List<WebElement> elements = driver.findElements(by);
			return elements;
		}catch(Exception e){
			System.out.println("FAILED to return web elements");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("FAILED to return web elements. Exception description : " + e.getMessage());
			return null;
		}
		  
	}

    /*************************************************************************
     * Method description: 
     * Clear previous value if needed; send keys/value, report failure if needed
     *************************************************************************/ 
	public void sendKeysTo(WebElement element, String keysToSend){
		  
		try{
			element.sendKeys();
			element.clear();
			element.click();
			element.sendKeys(keysToSend);
			wait.until(ExpectedConditions.textToBePresentInElementValue(element, keysToSend));
		}catch(Exception e){
			System.out.println("Failed to send keys to element");
			System.out.println("Exception description : " + e.getMessage());
			Assert.fail("Failed to send keys to element");	
		}
		  
	}

    /*************************************************************************
     * Method description: 
     * Clear previous value if needed; send keys/value, report failure if needed
     *************************************************************************/ 
	public void sendKeysTo(By by, String keysToSend){
		  
		WebElement element = getElement(by);
		sendKeysTo(element, keysToSend);
		  
	}
	
    /*************************************************************************
     * Method description: 
     * Verify if the element is in the DOM and displayed. 
     *************************************************************************/	
	  public boolean isElementPresentAndDisplay(WebElement aElement) {
		try {	
			waitForElement(aElement);
			return true;
		} catch (Exception e) {
			return false;
		}
	  }	
	  
    /*************************************************************************
     * Method description: 
     * Verify if the element is in the DOM and displayed. 
     *************************************************************************/	
	  public boolean isElementPresentAndDisplay(By by) {
		try {	
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	  }	  
		  
	/*************************************************************************
	 * Class description: 
     * Wait for element to be click able and click on it
    *************************************************************************/
	private class tryToClickOnElement implements ExpectedCondition<Boolean>{
		
		private WebElement element;
	
		public tryToClickOnElement(final WebElement aElement){
			this.element = aElement;
		}
		
		@Override
		public Boolean apply(WebDriver webDriver){
			
			try{
				element.click();
				return true;
			}catch(Exception e){
				return false;
			}		
		}		
	}		  
}
