package helen.apis;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helen.apiPojos.NewPost;

public class GetPostPageMeta {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		String message = "Hello?!-" + helen.utilities.Keywords.getTimeStamp();
		
		try {
			//login and create new post through UI
			helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
			
			String currentURL = driver.getCurrentUrl();
			if (!currentURL.equals("https://boratech-practice-app.onrender.com/dashboard")) {
				throw new Exception("Not on the dashboard page. Current URL: " + currentURL);
			}
			
			driver.findElement(By.xpath("//a[@href='/posts']")).click();
			driver.findElement(By.xpath("//textarea[@name='text']")).sendKeys(message);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/posts']")));
			//String uiText = driver.findElement(By.xpath("//p[@class='my-1']")).getText();
	
			
			//login and get new post text through API
			String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");	
			List <NewPost> actualPosts = helen.utilities.BoraTechApis.getNewPost(token);
			
			
			//validate two post texts
			boolean found = false;
			for (NewPost actualPost : actualPosts) {
				if (actualPost.text.equals(message)) {
					found = true;
					break;
				}
			}
			if (found) {
				System.out.println("Pass");
			} else {
				
			}
			
		} catch(Exception e) {
			System.out.println("Fail");
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}
	}
		
}
