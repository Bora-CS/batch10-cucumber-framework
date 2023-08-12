package hui_automation;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CostcoPractice0812 {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			testDriver.manage().window().maximize();
			testDriver.get("https://www.costco.com/");
			String mainHandle = testDriver.getWindowHandle();
			testDriver.findElement(By.xpath("//a[@href=\"https://costconext.com/\"]//p[text()='Costco NEXT']/../.."))
					.click();
			Set<String> handles = testDriver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(mainHandle))
					testDriver.switchTo().window(handle);
			}
			String url = testDriver.getCurrentUrl();
			if (!url.equals("https://costconext.com/"))
				throw new Exception("Wrong url.");
			boolean isTargetText = Testkeys.containsElement(testDriver, By.xpath("//*[text()='What is Costco Next?']"));
			if (!isTargetText)
				throw new Exception("Target text not found.");
			
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Testkeys.terminate(testDriver);
		}
	}

}
