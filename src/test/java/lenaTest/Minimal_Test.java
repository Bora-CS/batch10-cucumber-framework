package lenaTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;
import utilities.Lena_Methods;

public class Minimal_Test {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		try {
			Lena_Methods.login(driver, "demo@minimals.cc", "demo1234");
		} catch (InterruptedException e) {
			System.out.println("failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
