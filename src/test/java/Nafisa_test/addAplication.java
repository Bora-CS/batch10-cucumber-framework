package Nafisa_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Keywords;

public class addAplication {

		public static void main(String[] args) {
			WebDriver driver = new ChromeDriver();
			String firstname = "Nafisa";
			String lastname = "Xiaokaiti";
			String datetoBrith = "02/06/1996";
			String email = "nanfeise@gmail.com";
			String gender = "female";
			String phone ="270-438-0603";
			
			

			try {
				driver.navigate().to("https://boratech-practice-app.onrender.com/");
				driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
				Keywords.wait(2);

				String actualUrl = driver.getCurrentUrl();
				if (!actualUrl.endsWith("/apply")) {
					throw new Exception("Expected to have '/apply' in the url.\nCurrent URL: " + actualUrl);
				}

				String expectedTitleText = "Application Form";
				String actualTitleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
				if (!actualTitleText.equals(expectedTitleText)) {
					throw new Exception(
							"Title Text doesn't match.\nExpected: " + expectedTitleText + "\nActual: " + actualTitleText);
				}

				driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstname);
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
				driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(datetoBrith);
				driver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();
				Keywords.wait(2);
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
				driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phone);
				Keywords.wait(2);

				Select course = new Select(driver.findElement(By.xpath("//select[@name='course']")));
				course.selectByValue("aws");

				Select source = new Select(driver.findElement(By.xpath("//select[@name='source']")));
				source.selectByIndex(1);



				WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
				if (submitButton.isEnabled()) {
					throw new Exception("Submit button should be disabled");
				}

				driver.findElement(By.xpath("//input[@name='notarobot']")).click();

				if (!submitButton.isEnabled()) {
					throw new Exception("Submit button should be enabled");
				}

				submitButton.click();

				Keywords.wait(2);


				System.out.println("Test Passed");
			} catch (Exception e) {
				System.out.println("Test Failed");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} finally {
				driver.close();
				driver.quit();
			}

		}

	}
