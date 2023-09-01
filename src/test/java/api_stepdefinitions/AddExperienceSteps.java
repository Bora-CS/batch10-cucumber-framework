package api_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import apiPojos.Experience;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;
import utilities.DataManager;
import utilities.Keywords;

public class AddExperienceSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] users should be able to add a new experience")
	public void apiAddExperience(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();

		Experience expectedExperience = new Experience(data.get("company") + Keywords.getTimeStamp(), data.get("title"),
				data.get("location"), data.get("from"), data.get("to"), Boolean.valueOf(data.get("current")),
				data.get("description"));

		List<Experience> experiences = BoraTechAPIs.addExperience(dataManager.getToken(), expectedExperience);

		boolean found = false;
		for (Experience experience : experiences) {
			if (experience.equals(expectedExperience)) {
				found = true;
				break;
			}
		}

		assertTrue(found, "The newly added experience was not found");
	}

}
