package krystal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.BoraTech;


public class Data_Driven_Krystal {



		public static void main(String[] args) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			WebDriver driver = new ChromeDriver(options);

			String username = "krystal.lee.om@gmail.com";
			String password = "123456";

			
			
			Pojo_Education_Krystal edu1 = new Pojo_Education_Krystal("", "Bachelor", "Psychology",
					 "08/21/2011", "06/15/2015", false, "Studied psychological theories and practices, human behavior, mental health conditions, "
					 		+ "and cognitive processes",new String[] { "School is required" });
			
			Pojo_Education_Krystal edu2 = new Pojo_Education_Krystal("George Washigton", "", "Computer Science", "08/23/15", "06/12/17", false,
					"Studied advanced theory and applications of computer technology.", new String[] { "School is required", "Degree is required" });
			
			Pojo_Education_Krystal edu3 = new Pojo_Education_Krystal("BoraTech", "", "Test Automation", "05/07/23", "", true,
					"Thinking about how to automate everything everyday",
					new String[] { "Degree is required" });
			
			Pojo_Education_Krystal edu4 = new Pojo_Education_Krystal("", "Bachelor", "Psychology",
					 "08/21/2011", "06/15/2015", false, "",
					new String[] { "Field of study is required" });
			
			Pojo_Education_Krystal edu5 = new Pojo_Education_Krystal("BoraTech", "", "Test Automation", "05/07/23", "", true,
					"",
					new String[] { "Degree is required", "Field of study is required" });
			
			Pojo_Education_Krystal edu6 = new Pojo_Education_Krystal("BoraTech", "", "Test Automation", "", "", true,
					"Thinking about how to automate everything everyday",
					new String[] { "From date is required" });
			
			Pojo_Education_Krystal edu7 = new Pojo_Education_Krystal("BoraTech", "", "Test Automation", "", "", true,
					"Thinking about how to automate everything everyday",
					new String[] { "Degree is required" , "From date is required"});
			
			Pojo_Education_Krystal edu8 = new Pojo_Education_Krystal("BoraTech", "achelor", "", "", "", true,
					"Thinking about how to automate everything everyday",
					new String[] { "From date is required", "Field of study is required" });
			
			Pojo_Education_Krystal edu9 = new Pojo_Education_Krystal("", "Master", "Test Automation", "", "", true,
					"Thinking about how to automate everything everyday",
					new String[] { "School is required", "From date is required" });
			
			Pojo_Education_Krystal edu10 = new Pojo_Education_Krystal("BoraTech", "", "Test Automation", "", "", true,
					"",
					new String[] { "Degree is required", "Field of study is required",  "From date is required"  });

			
		
			Pojo_Education_Krystal[] educations = { edu1, edu2, edu3, edu4, edu5, edu6, edu7, edu8, edu9, edu10};

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
