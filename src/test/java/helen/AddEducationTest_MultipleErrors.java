package helen;


import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest_MultipleErrors {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		String username = "helenhjahn@gmail.com";
		String password = "06102021";
		String school = "";
		String degree = "";
		String fieldOfStudy = "Java, Selenium";
		String from = "05/01/2022";
		String to = "10/30/2022";
		boolean current = false;
		String description = "Learning all about Test Automation.";
		List<String> expectedErrors = new ArrayList<>();
		expectedErrors.add("School is required");
		expectedErrors.add("Degree is required");
		
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			wait(2);
			
			//validation on dashboard
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception ("Title Text not matching. Expected: Dashboard VS actual: " + titleText);
			}
			
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			
			//validation on education page 
			String titleText2 = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText2.equals("Add Your Education")) {
				throw new Exception ("Title Text not matching. Expected: Add Your Education VS actual: " + titleText2);
			}
			
			//adding education inputs
			driver.findElement(By.name("school")).sendKeys(school);
			driver.findElement(By.name("degree")).sendKeys(degree);
			driver.findElement(By.name("fieldofstudy")).sendKeys(fieldOfStudy);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			if (current ) {
				driver.findElement(By.xpath("//input[@name='current']")).sendKeys(to);
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			}
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait(2);
			
			//negative flow scenario with two inputs missing
			
			List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
			List<String> actualErrors = new ArrayList<>(); 
			for (WebElement errorAlert : errorAlerts) {
				actualErrors.add(errorAlert.getText());
			}
			
			//check for the number of errors match
			if (actualErrors.size() != expectedErrors.size()) {
				String message = "Number of errors doesn't match\n"
								+ "Expected: " + expectedErrors + "\n" + actualErrors;
				throw new Exception(message);
			}
			
			
			//compare the actual and expected errors
		
			for (String expectedError : expectedErrors) {
				if (!actualErrors.contains(expectedError)) {
					throw new Exception ("Expected Error not found - [" + expectedError + "]");
				}
			}
			

			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: "+ e.getMessage());	
		} finally {
			driver.close();
			driver.quit();	
		}
	}
	
	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second*1000);
	}
	
}
