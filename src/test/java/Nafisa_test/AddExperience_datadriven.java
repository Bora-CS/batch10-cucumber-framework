package Nafisa_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pojo.Experience;
import utilities.BoraTech;

public class AddExperience_datadriven {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String username = "nanfeise@gmail.com";
        String password = "Nafisa1996";

        Experience exp1 = new Experience("Social worker", "Church World Service", "Harrisonburg VA", "02/01/2018", "02/01/2019", false, "Helping others");
        Experience exp2 = new Experience("Social worker", "First choice", "Harrisonburg VA", "02/01/2017", "02/01/2018", false, "Organize client information");
        Experience exp3 = new Experience("Manager", "Jpetal", "Harrisonburg VA", "02/01/2019", "", true, "Manage store");

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

		
