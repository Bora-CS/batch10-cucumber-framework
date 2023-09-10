package hui_automation.selenium.boratech_tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Experience;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.PageManager;
import hui_automation.utilities.Testkeys;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = DriverManager.getInstance();
		DataManager dataManager = DataManager.getInstance();
		PageManager pages = PageManager.getInstance();
		try {
			dataManager.setExperienceUI(getTestExperienceData());
			pages.homePage().navigate();
			pages.homePage().clickOnLogin();
			Testkeys.pause(driver, 1);
			pages.loginPage().login("hui-pretender@outlook.com", "Hui123456");
			Testkeys.pause(driver, 2);
			pages.dashboardPage().isPageLoaded();
			pages.dashboardPage().clickOnAddExperience();
			Testkeys.pause(driver, 1);
			pages.addExperiencePage().addExperience(dataManager.getExperienceUI());
			Testkeys.pause(driver, 9);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DriverManager.reset();
			DataManager.reset();
			PageManager.reset();
		}
	}

	private static Experience getTestExperienceData() {
		Map<String, String> data = new HashMap<>();
		data.put("company", "Walmart " + Testkeys.getTimestamp());
		data.put("job title", "Cashier");
		data.put("location", "Manassas, Virginia");
		data.put("from date", "06/06/2006");
		data.put("to date", "11/11/2008");
		data.put("current", "false");
		data.put("job description", "Payment collection.");
		Experience experience = new Experience(data);
		return experience;
	}
}
