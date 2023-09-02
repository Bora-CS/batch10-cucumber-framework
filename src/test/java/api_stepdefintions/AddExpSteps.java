package api_stepdefintions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import apiPojos.Experience;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;
import utilities.DataManager;

public class AddExpSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] user should be able to add a new experience")
	public List<Experience> apiAddExperience(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();

		Experience expectedExperience = new Experience(data.get("company"), data.get("title"), data.get("location"),
				data.get("from"), data.get("to"), Boolean.valueOf("current"), data.get("description"));

		List<Experience> experiences = BoraTechAPIs.addExperience(dataManager.getToken, experience);

		Boolean found = false;

		for (Experience experience : experiences) {
			if (experience.equals(expectedExperience))
				;
			{
				found = true;
				break;
			}
		}

		assertTrue(found, "the newly added exp was not found");
	}

}
