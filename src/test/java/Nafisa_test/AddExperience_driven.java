package Nafisa_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pojo.Experience;
import utilities.BoraTech;


public class AddExperience_driven {
		

		public static void main(String[] args) {
			
			WebDriver driver = new ChromeDriver();
			String username = "nanfeise@gmail.com";
			String password = "Nafisa1996";
			

			Experience exp1 = new Experience("Cashier", "Jpetal", "Harrisonbrug, VA", "02/12/2018", "02/12/2019",
					false, "Just learning how to take orders and count some money");
			Experience exp2 = new Experience("Scoail worker", "CWS", "Harrisonburg, VA", "02/16/2019", "08/12/2020", false,
					"Helping imgration");
			Experience exp3 = new Experience("Jpetal", "Mnager", "harrisonburg, VA", "08/13/2020", "", true,
					"mange the store");

			Experience[] experiences = { exp1, exp2, exp3 };

		

			try {
				BoraTech.login(driver, username, password);

				for (Experience experience : experiences) {
					BoraTech.addExperience(driver, experience);
				}

				BoraTech.deleteAllExperiences(driver);

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

