package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pojo.Experience;
import utilities.BoraTech;
import utilities.Keywords;

public class addExperience_DataDriven {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "xiaoli1005@yahoo.com";
		String password = "Goodbora425!";

		Experience exp1 = new Experience("Junior Cashier", "McDonald's", "Annandale, VA", "02/12/2013", "02/12/2014",
				false, "Just learning how to take orders and count some money");
		Experience exp2 = new Experience("Cashier", "Subway", "Fairfax, VA", "02/16/2014", "08/12/2020", false,
				"Counting money everyday");
		Experience exp3 = new Experience("Senior Cashier", "Chik-fil-A", "Haymarket, VA", "08/13/2020", "", true,
				"Bro I know I still count money, but I eat 'healthy' nowdays");

		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			Keywords.wait(2);

			
			
			
			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}
	
	public static void addExpericnce(WebDriver driver, Experience experience) throws Exception {
	driver.findElement(By.xpath("//a[@href='add-experience']")).click();
	Keywords.wait(2);
	
	driver.findElement(By.xpath("//input[@name='title']")).sendKeys(experience.jobTitle);
	driver.findElement(By.xpath("//input[@name='company']")).sendKeys(experience.company);
	driver.findElement(By.xpath("//input[@name='location']")).sendKeys(experience.location);
	driver.findElement(By.xpath("//input[@name='from']")).sendKeys(experience.from);
	if (current) {
		driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
		}
	driver.findElement(By.tagName("textarea")).sendKeys(description);
	driver.findElement(By.xpath("//input[@ntype='submit']")).click();
	Keywords.wait(2);
	
	String tableXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]";
	String tableRowXpath = tableXpath + "/tbody/ty";
	
	List<WebElement> rows = driver.findElement(By.xpath(tableRowXpath));
	
	boolean found = false;
	for (WebElement row : rows) {
		
		List<WebElement> cells = row.findElement(By.tagName("td"));
		String actualCompany = cells.get(0).getText();
		String actualJobtitle = cells.get(1).getText();
		if (jobTitle.equals(actualJobTitle) && company.equals(actualCompany)) {
			found = true;
			break;
		}
		
		
	}
	
	if (!found) {
		throw new Exception("The newly entered experience was not found");
	}
	
	
}
