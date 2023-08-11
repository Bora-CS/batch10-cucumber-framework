package krystal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.BoraTech;
import pojo.Experience;

public class Data_Driven_Krystal {



		public static void main(String[] args) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			WebDriver driver = new ChromeDriver(options);

			String username = "krystal.lee.om@gmail.com";
			String password = "123456";

			Pojo_Education_Krystal edu1 = new Pojo_Education_Krystal("George Washington", "Bachelor", "Psychology", "08/21/2011", "06/15/2015",
					false, "Studied psychological theories and practices, human behavior, mental health conditions, and cognitive processes.");
			Pojo_Education_Krystal edu2 = new Pojo_Education_Krystal("George Washington", "Masters", "Computer Science", "08/23/15", "06/12/17", false,
					"Studied advanced theory and applications of computer technology.");
			Pojo_Education_Krystal edu3 = new Pojo_Education_Krystal("BoraTech", "Degree", "Test Automation", "05/07/23", "", true,
					"Bro I know I still count money, but I eat 'healthy' nowdays");

			Pojo_Education_Krystal[] educations = { edu1, edu2, edu3 };

			try {
				BoraTech.login(driver, username, password);

				for (Pojo_Education_Krystal education : educations) {
					BoraTech_Krystal.addEducations(driver, education);
				}
				
				
				BoraTech_Krystal.deleteAllEducations(driver);

				System.out.println("Test Passed");
			} catch (Exception e) {
				System.out.println("Test Failed");
				System.out.println("Reason: " + e.getMessage());
				e.printStackTrace();
			} finally {
				driver.close();
				driver.quit();
			}

		}

	}
