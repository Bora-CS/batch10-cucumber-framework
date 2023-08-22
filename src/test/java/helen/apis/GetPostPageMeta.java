package helen.apis;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import helen.apiPojos.NewPost;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPostPageMeta {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//		driver.manage().window().maximize();
		
		String message = "Hello?!-" + helen.utilities.Keywords.getTimeStamp();
		

		//login and create new post through UI
		helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		driver.findElement(By.xpath("//a[@href='/posts']")).click();
		driver.findElement(By.xpath("//textarea[@name='text']")).sendKeys(message);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//String uiText = driver.findElement(By.xpath("//p[@class='my-1']")).getText();

		
		//login and get new post text through API
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");	
		List <NewPost> actualPosts = helen.utilities.BoraTechApis.getNewPost(token, message);
		
		boolean found = false;
		for (NewPost actualPost : actualPosts) {
			if (actualPost.text.equals(message)) {
				found = false;
				break;
			}
	
		if (found) {
			System.out.println("Pass. New Post found is " + actualPost.text);
		} else {
			System.out.println("Fail. \nExpected Post: " + message + "\nActual Post: " + actualPost.text );
		}
			
			
		driver.close();
		driver.quit();
		
		}
			
	}
}
