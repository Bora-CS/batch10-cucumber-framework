package ardal_practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ardal_practice.pojo.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Keywords;

public class AddingNewPostUltimate {

	public static void main(String[] args) {

		String expectedPost = "Is there something I'm missing? " + Keywords.getTimeStamp();

		AddingNewPost(expectedPost);
		
		UiValidation(expectedPost);

		APIValidation(expectedPost);

	}

	public static void AddingNewPost(String expectedPost) {
		try {
			WebDriver driver = new ChromeDriver();
			driver.get("https://boratech-practice-app.onrender.com");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();

			String username = "ardal002713@gmail.com";
			String password = "ardal123";

			BoraTechArdal.login(driver, username, password);
			driver.findElement(By.xpath("//a[text()='Posts']")).click();
			driver.findElement(By.name("text")).sendKeys(expectedPost);
			driver.findElement(By.xpath("//input[@value='Submit']")).click();

			driver.navigate().refresh();
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Something went wrong" + e.getMessage());
		} finally {
			WebDriver driver = new ChromeDriver();
			driver.close();
			driver.quit();
		}
	}

	public static void UiValidation(String expectedPost) {
		WebDriver driver = new ChromeDriver();

		List<WebElement> posts = driver
				.findElements(By.xpath("//div[@class='post bg-white p-1 my-1']/div/p[@class='my-1']"));

		boolean found = false;
		for (WebElement actualPostBody : posts) {
			if (expectedPost.equals(actualPostBody.getText())) {
				found = true;
				break;
			}

		}

		if (found) {
			System.out.println("UI Validation passed, Expected Post found");
		} else {
			System.out.println("UI Validation failed, Expected Post not found");
		}

		driver.close();
		driver.quit();
	}

	public static void APIValidation(String expectedPost) {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		String token = BoraTechAPIs.login("ardal002713@gmail.com", "ardal123");
		request.header("x-auth-token", token);

		Response response = request.get("/api/posts");

		JsonPath jp = response.jsonPath();
		ArrayList<String> posts = jp.get("text");

		boolean found = false;
		;
		for (String post : posts) {
			if (expectedPost.equals(post)) {
				found = true;
				break;
			}
		}
		if (found) {
			System.out.println("API Validation passed, Expected Post found");
		} else {
			System.out.println("API Validation failed, Expected Post not found");
		}
	}

}
