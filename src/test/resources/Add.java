
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEducationTest {

	public static void main(String[] args) {

		WebDriver driver = new Chromedriver();
	
		try {
			
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("kpaljor306@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']"))sendKeys("Bora123");
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//a[@href='#!']/span"));
			
			
			
			System.out.println("Pass");	
			
		} catch (Exception e) {
			
			System.out.println("Fail");
		}
		
		finally {
			driver.quit();
		}
	}

}
