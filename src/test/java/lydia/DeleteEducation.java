package lydia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lydia.utilities.BoraTech_Utilities;


public class DeleteEducation {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String username = "w.lydia.liu@gmail.com";
		String password = "Liu123456";
		
		try {
			BoraTech_Utilities.login(driver, username, password);
			
			BoraTech_Utilities.deleteAllEducations(driver);

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
