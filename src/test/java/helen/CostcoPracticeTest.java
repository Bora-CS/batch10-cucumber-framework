package helen;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class CostcoPracticeTest{

	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver","D://Selenium Environment//Drivers//geckodriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		String city = "fairfax";
		String expectedWarehouse = "Fairfax";

		
		try {
			driver.get("https://www.costco.com/");
			driver.findElement(By.xpath("//img[@src='https://media-cdn.costco.com/www3-media/?libBID=3912504']")).click();
//			driver.findElement(By.xpath("//img[@alt='Huggies Plus Diapers Sizes 3 - 7']")).click();
	
			//creating a reference and calling the method to perform "scroll down"
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			
			//scrolling down the page till the targetelement is found 	        		
	        WebElement targetElement = driver.findElement(By.xpath("//img[@alt='Huggies Plus Diapers Sizes 3 - 7']"));
	        js.executeScript("arguments[0].scrollIntoView();", targetElement);
	        
	        Keywords.wait(2);
	        
	        //go back to previous webpage
	        driver.navigate().back();
	    
	        //scrolling down the page till the targetelement2 is found -> enter city input and submit
			WebElement targetElement2 = driver.findElement(By.xpath("//input[@id='footer-search-field']")); 
			js.executeScript("arguments[0].scrollIntoView();", targetElement2);
			targetElement2.sendKeys(city);
			targetElement2.submit();
			
			//validate if the new webpage is correct
			String currentUrl = driver.getCurrentUrl();		
			if (!currentUrl.endsWith("/warehouse-locations?location=fairfax&fromWLocSubmit=true&numOfWarehouses=10")) {
				throw new Exception("Page URL is wrong, Expected URL contains: /numOfWarehouses=10 vs Actual URL: " + currentUrl);
			}
			
			//click 'Set as my warehouse' button
			driver.findElement(By.xpath("//*[@id=\"set-as-my-warehouse\"]/td[2]/div/div/input")).click();
			
			Keywords.wait(2);
			
			//validate if "My Warehouse" is set as the selected one
			String actualWarehouse = driver.findElement(By.xpath("//*[@aria-label='Fairfax, current warehouse']")).getText();
//			System.out.println(actualWarehouse);
			
			if (!actualWarehouse.equals(expectedWarehouse)) {
				throw new Exception("'My Warehouse should be displayed correctly. Expected Warehouse: " + expectedWarehouse + " vs Actual warehouse: " + actualWarehouse);
			}
			
			
			System.out.println("Test Passed");
			
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println(e.getMessage());
//			e.printStackTrace();  //delete later
			
		} finally {
			driver.close();
			driver.quit();
		}
		
	}	
	
	
}
