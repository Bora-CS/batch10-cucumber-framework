package rubenTrials;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class DataDrivenApp {
	
	public static void main (String[] args) {

		try {
			HashMap<String, String> test_3 = new HashMap<>();
			test_3.put("firstName", "John");
			test_3.put("lastName", "Doe");
			test_3.put("email", "xxx@gmail.com");
			test_3.put("userNumber", "9999999999");
			submitApplication(test_3);
			Thread.sleep(3000);
		} catch(Exception e) {
			System.out.println("test Failed");
			System.out.println("reason being" + e.getMessage());
			
		} 
		
		
		
	}

	public static void submitApplication(HashMap<String, String> formData) {

		WebDriver localDriver = driverFactory();
		
		
		localDriver.navigate().to("https://demoqa.com/automation-practice-form");
		
		
		
		for (String key : formData.keySet()) {
			
			
			switch (key) {
			
			case "firstName":
				localDriver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(formData.get(key));
				break;
				
			case "lastName":
				localDriver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(formData.get(key));
				break;
				
			case "email":
				localDriver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys(formData.get(key));
				break;

			case "userNumber":
				localDriver.findElement(By.xpath("//*[@id='userNumber']")).sendKeys(formData.get(key));
				
				
				
			}

			

		}

	}

	public static WebDriver driverFactory() {

		WebDriver driver = new ChromeDriver();
		return driver;
		
	}
}
