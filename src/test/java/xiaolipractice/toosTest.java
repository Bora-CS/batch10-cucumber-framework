package xiaolipractice;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class toosTest {
	public static void main(String[] args) {
		HashMap<String,String> test_1 = new HashMap<>();
		test_1.put("lastName","John");
		submitApplicationForm(test_1);
		
		HashMap<String,String> test_2 = new HashMap<>();
		test_2.put("firstName","John");
		test_2.put("email","test@t.com");
		submitApplicationForm(test_2);
		
		HashMap<String,String> test_3 = new HashMap<>();
		test_3.put("firstName","John");
		test_3.put("email","test@t.com");
		test_3.put("phonenumber","703-222-2222");
		submitApplicationForm(test_3);
		
	}

	public static void submitApplicationForm(HashMap<String,String> formData) {
		
		WebDriver localDriver = driverFactory();

		localDriver.get("https://demoqa.com/automation-practice-form");
		
		for (String key : formData.keySet()) {
			switch (key.toLowerCase()) {
			
			case "firstname":
		
			localDriver.findElement(By.id("firstName")).sendKeys(formData.get(key));
			break;
			case "lastname":
			localDriver.findElement(By.id("lastName")).sendKeys(formData.get(key));  
			break;
			case "useremail":
			localDriver.findElement(By.id("userEmail")).sendKeys(formData.get(key)); 
			break;
			case "phonenumber":
			localDriver.findElement(By.id("userNumber")).sendKeys(formData.get(key));
			break;
			case "subject":
			localDriver.findElement(By.id("subject")).sendKeys(formData.get(key));
			break;	
			
			
			
			
		}	

	}
		
	}
	public static WebDriver driverFactory() {
		
	WebDriver driver = new ChromeDriver();
	return driver;
}
}