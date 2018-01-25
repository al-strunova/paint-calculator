package test.testcases;

import main.pages.DimensionsPage;
import main.pages.HomePage;
import main.pages.ResultPage;
import main.utility.Driver;
import main.utility.Util;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PaintCalculatorTest {
	
	@Parameters({ "browser", "baseURL"})
	@Test
	public void verifyPaintCalculator(@Optional("chrome") String aBrowserName, @Optional("http://127.0.0.1:5000/") String aBasedURL){
		
		//Navigate to home page
		Driver.setBrowserName(aBrowserName);
		WebDriver driver = Driver.get(aBasedURL);
		HomePage homePage = new HomePage(driver);
				
		//Verify that "Enter the number of rooms" field is displayed.
		Assert.assertTrue(homePage.isNbrOfRoomFieldDisplayed(), "Verify that 'Enter the number of rooms' field is displayed on Home page");
		
		//Verify that "Submit" button is displayed.
		Assert.assertTrue(homePage.isSubmitBtnDisplayed(), "Verify that 'Submit' button is displayed on Home page");
			
		//Enter some value to "Enter the number of rooms" field.
		int nbrOfRoomsArr[] = {1,2};
		
		//Execute following steps for different values in nbrOfRoomsArr
		for(int nbrOfRooms: nbrOfRoomsArr){
			
			homePage.setValueToNbrOfRoomsField(nbrOfRooms);
			
			//Click on "Submit" button.
			homePage.clickOnSubmitBtn();
			
			//Verify that the user is taken to the dimensions page.
			DimensionsPage dimensionsPage = new DimensionsPage(driver);
	
			//Verify that the number of rows in the table is the same as the one entered in "Enter the number of rooms" field on base page.
			Assert.assertTrue(dimensionsPage.getNbrOfRoomRows() == nbrOfRooms, "Verify that the number of rows in the table is the same as the one entered in 'Enter the number of rooms' field on Home page.");
			
			//Verify that "Submit" button is displayed.
			Assert.assertTrue(dimensionsPage.isSubmitBtnDisplayed(), "Verify that 'Submit' field is displayed on Dimensions page");
			
			//Verify that "Length", "Width", "Height" fields for each row
			//And set values in "Length", "Width", "Height" fields for each row
			int areaToPaintArr[] = new int[nbrOfRooms];
			
			//Execute next steps for each room
			for(int currentRoom =1; currentRoom <=nbrOfRooms; currentRoom++){
				
				Assert.assertTrue(dimensionsPage.isLengthFieldDisplayed(currentRoom), "Verify that 'Length' field is displayed for >" + currentRoom + "< room on Dimensions page.");
				Assert.assertTrue(dimensionsPage.isWidthFieldDisplayed(currentRoom), "Verify that 'Width' field is displayed for >" + currentRoom + "< room on Dimensions page.");
				Assert.assertTrue(dimensionsPage.isHeightFieldDisplayed(currentRoom), "Verify that 'Height' field is displayed for >" + currentRoom + "< room on Dimensions page.");
			
				//Enter some values to "Length", "Width", "Height" fields
				int lengthValue = Util.randomGenerator(10,50);
				dimensionsPage.setValueToLengthField(lengthValue, currentRoom);
				int widthValue = Util.randomGenerator(10,50);
				dimensionsPage.setValueToWidthField(widthValue, currentRoom);
				int heightValue = Util.randomGenerator(10,50);
				dimensionsPage.setValueToHeightField(heightValue, currentRoom);
				
				//Caluculate the surface area to paint
				areaToPaintArr[(currentRoom-1)] = ((lengthValue * 2) + (widthValue * 2)) * heightValue;
				
			}
			
			//and click on "Submit" button.
			dimensionsPage.clickOnSubmitBtn();
			
			//Verify that the user is taken to the results page.
			ResultPage resultPage = new ResultPage(driver);
					
			//Verify that the number of rows in the table is the same as the one entered in "Enter the number of rooms" field on base page.
			Assert.assertTrue(resultPage.getNbrOfRoomRows() == nbrOfRooms, "Verify that the number of rows in the table is the same as the one entered in 'Enter the number of rooms' field on Home page.");
			
			//Execute next steps for each room
			int totalGallonsRequired = 0;
			for(int currentRoom =1; currentRoom <=nbrOfRooms; currentRoom++){
				
				//Verify that the value for 'Amount of Feet to Paint' column is displayed and calculated properly.
				int actualAmountOfFeetToPaint = resultPage.getAmountOfFeetToPaint(currentRoom);
				int expectedAmountOfFeetToPaint = areaToPaintArr[(currentRoom-1)];
				Assert.assertTrue(actualAmountOfFeetToPaint == expectedAmountOfFeetToPaint, "Verify that the value for 'Amount of Feet to Paint' column is displayed and calculated properly for >" + currentRoom + "< room on Results page.");
							
				//Verify that the value for 'Gallons required' column is displayed and calculated properly.
				int actualGallonsRequired = resultPage.getGallonsRequired(currentRoom);
				double expectedGallonsRequired = Math.ceil((double)expectedAmountOfFeetToPaint/400);
				Assert.assertTrue(actualGallonsRequired == expectedGallonsRequired, "Verify that the value for 'Gallons required' column is displayed and calculated properly for >" + currentRoom + "< room on Results page.");
				
				totalGallonsRequired = totalGallonsRequired + actualGallonsRequired;
			}
	
			//Verify that the value for 'Total Gallons Required:' is displayed and calculated properly.
			int actualTotalGallonsRequired = resultPage.getTotalGallonsRequired();
			Assert.assertTrue(actualTotalGallonsRequired == totalGallonsRequired, "Verify that the value for 'Total Gallons Required:' text is displayed and calculated properly on Results page");
			
			//Click on Home button
			resultPage.clickHomeBtn();
			
			homePage = new HomePage(driver);
		
		}

	}

}
