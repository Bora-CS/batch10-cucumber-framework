package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.api_pojos.Post;
import hui_automation.utilities.BoraTech;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class PostsPageCombinedTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		try {
			BoraTech.login(driver, email, password);
			driver.findElement(By.xpath("//a[@href='/posts']")).click();
			wait.until(ExpectedConditions.urlContains("posts"));

			// add a post
			String postContent = "I want to fight a fish. " + Testkeys.getTimestamp();
			driver.findElement(By.tagName("textarea")).sendKeys(postContent);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.pause(driver, 3);
			driver.close();

			// validate through API
			String token = BoraTechAPIs.login(email, password);
			List<Post> allPosts = BoraTechAPIs.getBoraTechPosts(token);
			boolean targetPost = false;
			for (Post actualPost : allPosts) {
				if (actualPost.text.equals(postContent)) {
					targetPost = true;
					System.out.println(actualPost.name + ": " + actualPost.text);
					break;
				}
			}

			if (targetPost)
				System.out.println("Test passed.");
			else
				System.out.println("Test failed!");
		} catch (Exception e) {
			System.out.println("Test failed!");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
		}
	}

}
