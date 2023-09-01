package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Experience;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AddExperienceSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] user adds a new [Experience]")
	public void api_user_adds_a_new_experience(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Experience expData = new Experience(data.get("company") + " " + Testkeys.getUniqueMillsTimeStr(),
				data.get("title"), data.get("location"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"));
		List<Experience> allExperiences = BoraTechAPIs.putExperience(dataManager.getToken(), expData);
		boolean targetFound = false;
		for (Experience actualExperience : allExperiences) {
			if (actualExperience.equals(expData)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
		System.out.println(expData.company + ", " + expData.title);
	}

}
