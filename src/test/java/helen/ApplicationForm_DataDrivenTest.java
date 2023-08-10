package helen;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Keywords;

public class ApplicationForm_DataDrivenTest {
	

	public static void main(String[] args) {
		
		HashMap <String, String> test_1 = new HashMap<>();
		test_1.put("firstName", "John");
		submitApplicationForm(test_1);
		
		HashMap <String, String> test_2 = new HashMap<>();
		test_2.put("firstName", "John");
		test_2.put("email", "test@t.com");
		submitApplicationForm(test_2);
		
		HashMap <String, String> test_3 = new HashMap<>();
		test_3.put("firstName", "John");
		test_3.put("phoneNumber", "123-456-7890");
		test_3.put("subject", "STED");
		submitApplicationForm(test_3);
		
		//positive testing
		HashMap <String, String> test_4 = new HashMap<>();
		test_4.put("firstName", "John");
		test_4.put("lastName", "Smith");
		test_4.put("gender", "female");
		test_4.put("phoneNumber", "1234567890");
		test_4.put("subject", "STED");
		test_4.put("firstName", "John");
		submitApplicationForm(test_4);
		
		
		//negative testing1
		HashMap <String, String> test_5 = new HashMap<>();
		test_5.put("firstName", "John");
		test_5.put("lastName", "Smith");
		test_5.put("phoneNumber", "123-456-7890");
		test_5.put("subject", "STED");
		submitApplicationForm(test_5);
		
	}
	
	
	public static void submitApplicationForm(HashMap<String, String> formData) {
		
		WebDriver localDriver = driverFactory();
		
		try {
			
			
			
			localDriver.get("https://demoqa.com/automation-practice-form");
			
			for (String key: formData.keySet()) {
				
				switch (key.toLowerCase()) {
				
				case "firstName":
					localDriver.findElement(By.id("firstName")).sendKeys(formData.get(key));
					break;
				case "lastName": 
					localDriver.findElement(By.id("lastName")).sendKeys(formData.get(key));
					break;
				case "email":
					localDriver.findElement(By.id("userEmail")).sendKeys(formData.get(key));
					break;
				case "phoneNumber":
					localDriver.findElement(By.id("userNumber")).sendKeys(formData.get(key));
					break;
				case "subject":
					localDriver.findElement(By.id("subjectsInput")).sendKeys(formData.get(key));
					break;
				case "gender":
					selectGender(localDriver, formData.get(key));
				}	
			}
			
		} catch (Exception e) {
			System.out.println("Invalid input");
		} finally {
			
//			localDriver.findElement(By.id("submit")).click();
			jsClick(localDriver, By.id("submit"));
//			Keywords.wait(3);
			localDriver.close();
			localDriver.quit();
		
		}
	
	
 }	

	
	private static void selectGender(WebDriver driver, String gender) {
		if( gender.equalsIgnoreCase("male")) {
			driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();
		}else if(gender.equalsIgnoreCase("female")) {
			driver.findElement(By.xpath("//*[@for='gender-radio-2']")).click();
		}else {
			driver.findElement(By.xpath("//*[@for='gender-radio-3']")).click();
		}
	}
	
	static void  jsClick (WebDriver driver, By by) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(by));
	}


	public static WebDriver driverFactory() {
		WebDriver driver = new ChromeDriver();   //global driver
		return driver;	
	}

}
