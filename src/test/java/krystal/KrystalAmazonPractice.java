package krystal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KrystalAmazonPractice {
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		String search = "coding tshirt";
		
		
		
		try {
			
			driver.navigate().to("https://www.amazon.com/ref=nav_logo");
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(search);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='Go']")).click();
			Thread.sleep(2000);
			
			
			driver.findElement(By.xpath("//span[text()='Funny Programmer T-Shirt - Code Works Why Meme Tee']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@aria-labelledby='fit_type_2-announce']")).click();
			driver.findElement(By.xpath("//input[@aria-labelledby='size_name_1-announce']")).click();
			driver.findElement(By.xpath("//input[@formaction='/cart/add-to-cart/ref=dp_start-bbf_1_glance']")).click();
			Thread.sleep(2000);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
		
		
	}
	
	

}
