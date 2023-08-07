package helen;

import java.sql.Timestamp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {
	
		WebDriver driver = new ChromeDriver();
		
		String username = "helenhjahn@gmail.com";
		String password = "06102021";
		String jobTitle = "Test Automation Engineer";
		String company = "Bora Tech" + getTimeStamp();
		String location = "Bora Tech";
		String from = "05/01/2022";
		String to = "10/01/2022";
		boolean current = false;
		String description = "Build and maintain an automation framework for the software using Selenium.";
		
		
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			
			wait(2);
			
			//validation on dashboard page
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception ("Title Text not matching. Expected: Dashboard VS actual: " + titleText);
			}
			
			//move to experience page
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			wait(2);
			
			//validation on experience page
			String titleText2 = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText2.equals("Add An Experience")) {
				throw new Exception ("Title Text not matching. Expected: Add An Experience VS actual: " + titleText2);
			}
			
			//adding experience inputs
			driver.findElement(By.name("title")).sendKeys(jobTitle);
			driver.findElement(By.name("company")).sendKeys(company);
			driver.findElement(By.name("location")).sendKeys(location);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			if (current) {
				driver.findElement(By.xpath("//input[name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			}
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait(2);
			
			//validation for inputs
			String tableXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]";
			String tableRowXpath = tableXpath + "/tbody/tr";

			List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));
			
			boolean found = false;
			for(WebElement row:rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				String actualCompany = cells.get(0).getText();  //1st webelement
				String actualJobTitle = cells.get(1).getText(); 
				if (jobTitle.equals(actualJobTitle) && company.equals(actualCompany)) {
					found = true;
					break;
				} 
				
				if (!found) {
					throw new Exception ("The newly entered experience was not found");	
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
		Thread.sleep(second * 1000);
	}
	
	
	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "";
	}
	
	
	
}
