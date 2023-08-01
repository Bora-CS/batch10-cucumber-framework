package anthony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class deleteLoopTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("anth0ny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("PaSsWoRd123!");
			driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
			Thread.sleep(5000);
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception("Title text does not match. \n Title text should be: " + titleText);
			}
            int totalDeleteButtons = driver.findElements(By.cssSelector(".btn-danger")).size();

            for (int i = 0; i < totalDeleteButtons; i++) {
                WebElement deleteButton = driver.findElement(By.cssSelector(".btn-danger"));
                deleteButton.click();
                Thread.sleep(2000);
			}

			System.out.println("deletion success!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("We got an error. Checkout : " +e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
