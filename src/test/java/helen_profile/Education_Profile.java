package helen_profile;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Education_Profile {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("helenhjahn@gmail.com");
			driver.findElement(By.name("password")).sendKeys("06102021");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(2000);
			
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
			
			//adding inputs
			driver.findElement(By.name("school")).sendKeys("BoraTech Bootcamp");
			driver.findElement(By.name("degree")).sendKeys("Test Automation Engineer");
			driver.findElement(By.name("fieldofstudy")).sendKeys("Java, Selenium");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("05/01/2022");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("10/30/2022");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Learning all about Test Automation.");	
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//validation for inputs..
			
			
			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: "+ e.getMessage());	
		} finally {
			driver.quit();	
		}
	}
		
	
}
