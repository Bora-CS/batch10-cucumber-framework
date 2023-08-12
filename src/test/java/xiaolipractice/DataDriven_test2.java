package xiaolipractice;


import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;


public class DataDriven_test2 {


public static void main(String[] args) {
	HashMap<String, String> test_1 = new HashMap<>();
	test_1.put("lastName", "John");
	submitApplicationForm(test_1);
	
	HashMap<String, String> test_2 = new HashMap<>();
	test_2.put("firstName", "John");
	test_2.put("email", "test@t.com");
	submitApplicationForm(test_2);
	
	HashMap<String, String> test_3 = new HashMap<>();
	test_3.put("firstName", "John");
	test_3.put("email", "test@t.com");
	test_3.put("phonenumber", "703-222-2222");
	submitApplicationForm(test_3);
	
}

public static void submitApplicationForm(HashMap<String, String> formData) {

	WebDriver localDriver = driverFactory();

	localDriver.get("https://demoqa.com/automation-practice-form");

	for (String key : formData.keySet()) {
		switch (key.toLowerCase()) {

		case "firstname":
//			enterFirstName();
			localDriver.findElement(By.id("firstName")).sendKeys(formData.get(key));
			break;
		case "lastname":
			localDriver.findElement(By.id("lastName")).sendKeys(formData.get(key));
			break;
		case "email":
			localDriver.findElement(By.id("userEmail")).sendKeys(formData.get(key));
			break;
		case "phonenumber":
			localDriver.findElement(By.id("userNumber")).sendKeys(formData.get(key));
			break;
		case "subject":
			localDriver.findElement(By.id("subjectsInput")).sendKeys(formData.get(key));
			break;
		case "gender":
			selectGender(localDriver, formData.get(key));
		
		}
	}

//	localDriver.findElement(By.id("submit")).click();
	jsClick(localDriver, By.id("submit"));
	try {
		Keywords.wait(3);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	localDriver.close();
	localDriver.quit();
}


private static void selectGender(WebDriver driver, String gender) {
	if( gender.equalsIgnoreCase("male")) {
		driver.findElement(By.id("gender-radio-1")).click();
	}else if(gender.equalsIgnoreCase("female")) {
		driver.findElement(By.id("gender-radio-2")).click();
	}else {
		driver.findElement(By.id("gender-radio-3")).click();
	}
}

static void jsClick(WebDriver driver, By by) {
	JavascriptException executor = (JavascriptException) driver;
	
    executor.executeScript("arguments[0].click();", driver.findElement(by));
}











public static WebDriver driverFactory() {

	WebDriver driver = new ChromeDriver();
	return driver;

}

}





