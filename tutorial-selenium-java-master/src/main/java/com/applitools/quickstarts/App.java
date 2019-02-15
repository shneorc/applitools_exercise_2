package com.applitools.quickstarts;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;

/**
 * ---------------------
 * How to Execute
 * ---------------------
 * Must have Firefox browser installed, as this test uses firefox.
 * From cmd, navigate to the project folder and paste the command below ->
 * 		mvn exec:java -Dexec.mainClass="com.applitools.quickstarts.App"
 * 
 */


public class App {

	public static void main(String[] args) {


		
		WebDriver driver = new FirefoxDriver();

		// Check if the Applitools API key is set in the environment
		if (System.getenv("APPLITOOLS_API_KEY") == null) {
			System.out.println("\n\n**** Please set APPLITOOLS_API_KEY in your environment ***");
			System.out.println("On Mac: export APPLITOOLS_API_KEY='YOUR_API_KEY'");
			System.out.println("On Windows: set APPLITOOLS_API_KEY='YOUR_API_KEY'");
			System.exit(0);
		}

		// Initialize the eyes SDK and set private API key.
		Eyes eyes = new Eyes();

		// Set the API key from the env variable.
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		
		try {


			// Start the test by eyes.open()
			eyes.open(driver, "helloworld2", "window1", new RectangleSize(1200,750));

			// Navigate the browser to the "helloworld2" demo app
			driver.get("https://applitools.com/helloworld2");

			// Visual checkpoint #1. before entering the name.
			eyes.checkWindow("window1");
			
			//Enter details automatically.
			driver.findElement(By.id("name")).click();
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("Shneor Cheshin");
			driver.findElement(By.id("button")).click();
			
			// Visual checkpoint #2. After entering the name.
			eyes.checkWindow("window2");
			
			// End the test.
			eyes.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			// Close the browser.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortIfNotClosed();

			// End main test
			System.exit(0);
		}

	}
}
