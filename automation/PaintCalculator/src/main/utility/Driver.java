package main.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * A singleton style manager to maintain Drivers to prevent
 * test slow down for creating a browser for each class with tests.
 */
public class Driver extends Thread{
	
    private static WebDriver aDriver = null;
    private static final String DEFAULT_BROWSER = "FIREFOX";
    private static String browserName = null;
	private static boolean avoidRecursiveCall;

    /*************************************************************************
     * Function description: 
     * Set the name of the browser
     * Close any existing driver		
     *************************************************************************/
    public static void setBrowserName(String aBrowserName){
    	
    	//set the name of the browser
    	browserName = aBrowserName;
    	
        // close any existing driver
        if(aDriver != null){
            aDriver.quit();
            aDriver = null;   		
        }
    }

    /*************************************************************************
     * Function description: 
     * Get browser name	
     *************************************************************************/
    public static String getBrowserName(){    	
    	return browserName;    	
    }
    
    /*************************************************************************
     * Function description: 
     * Set a web driver
     * Note: if browser name was not set in testng.xml file, default browser will be set.		
     *************************************************************************/
    public static WebDriver get() {

    	//Check if browser was set. If browser wasn't set, use default browser
       	if (browserName.isEmpty()||browserName == null){
        	browserName = DEFAULT_BROWSER;
        	System.out.println("Browser was not set in testng.xml file. Default browser will be set. Default browser : > " + browserName + " <" );
       	}
        
        browserName = browserName.toUpperCase();

        //Launch browser
        if(aDriver==null){

            switch (browserName) {
                case "FIREFOX":
                	System.out.println("Launch Firefox browser");
                	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                    aDriver = new FirefoxDriver();
                    break;

                case "CHROME":
                	System.out.println("Launch Google Chrome browser");
                	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                	ChromeOptions options = new ChromeOptions();
                	options.addArguments("--disable-popup-blocking");
                	aDriver = new ChromeDriver(options);
                    break;
                    
                default:
                	System.out.println("Unknown Browser: " + browserName);
                    throw new RuntimeException("Unknown Browser: " + browserName);   
            }  
            
            // we want to shutdown the shared browser when the tests finish
            Runtime.getRuntime().addShutdownHook(
                    new Thread(){
                        public void run(){
                            Driver.quit();
                        }
                    });
        }else{

            try{
                // is browser still alive
                if(aDriver.getWindowHandle()!=null){
                    // assume it is still alive
                }
            }catch(Exception e){
		        if(avoidRecursiveCall){
		            // something has gone wrong as we have been here already
		        	System.out.println("Something has gone wrong");
		            throw new RuntimeException();
		        }
            	 	
            	quit();
                aDriver=null;
		        avoidRecursiveCall = true;                
                return get();
            }
        }

		avoidRecursiveCall = false;
        return aDriver;
    }

    
    /*************************************************************************
     * Function description: 
     * Open browser, maximize it if needed and return web driver to use it later 		
     *************************************************************************/
    public static WebDriver get(String aURL, boolean maximize){
    	
    	//Get web driver
        get();
        
        //Go to specified URL
        System.out.println("Go to > " + aURL + " < url");
        aDriver.get(aURL);
        
        //Maximize browser if needed
        if(maximize){
            try{
                aDriver.manage().window().maximize();
            }catch(Exception e){
            	System.out.println("Something has gone wrong: " + e.getMessage());
            }
        }
        
        return aDriver;
    }

    
    /*************************************************************************
     * Function description: 
     * Open browser, maximize it and return web driver to use it later 		
     *************************************************************************/
    public static WebDriver get(String aURL){
        return get(aURL,true);
    }

    
    /*************************************************************************
     * Function description: 
     * Close all opened browsers		
     *************************************************************************/
    public static void quit(){
    	
    	System.out.println("Close all opened browsers");
    	
    	//Check if browser still open
        if(aDriver!=null){
        	
        	//Close browser
            try{
                aDriver.quit();
                aDriver=null;
            }catch(Exception e){
                // I don't care about errors at this point
            }
        }
    }
 

    
}
