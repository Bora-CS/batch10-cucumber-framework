package helen;


import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest_SingleError {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		String username = "helenhjahn@gmail.com";
		String password = "06102021";
		String school = "BoraTech Bootcamp";
		String degree = "Test Automation Engineer";
		String fieldOfStudy = "Java, Selenium";
		String from = "05/01/2022";
		String to = "10/30/2022";
		boolean current = false;
		String description = "Learning all about Test Automation.";
		String expectedErrorMessage = "School is required";
		
		
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
			
			//validation for inputs 
			List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert danger']"));
			if (errorAlerts.size() !=1) {
				throw new Exception ("Received more than 1 error message. ");
			}
			
			String actualErrorMessage = errorAlerts.get(0).getText();
			if (!actualErrorMessage.equals(expectedErrorMessage)) {
				throw new Exception ("Error mismatch. Expected [" + expectedErrorMessage + "] Actual [" + actualErrorMessage + "]");
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
