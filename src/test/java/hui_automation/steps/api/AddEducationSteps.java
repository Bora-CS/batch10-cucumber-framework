package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Education;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AddEducationSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] user adds a new [Education]")
	public void api_user_adds_a_new_education(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Education eduData = new Education(data.get("school") + " " + Testkeys.getUniqueMillsTimeStr(),
				data.get("degree"), data.get("fieldofstudy"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"));
		List<Education> allEducations = BoraTechAPIs.putEducation(dataManager.getToken(), eduData);
		boolean targetFound = false;
		for (Education actualEducation : allEducations) {
			if (actualEducation.equals(eduData)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
		System.out.println(eduData.school + ", " + eduData.degree);
	}

}
