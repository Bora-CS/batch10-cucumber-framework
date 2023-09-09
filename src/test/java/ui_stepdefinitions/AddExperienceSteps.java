package ui_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import apiPojos.ApiError;
import apiPojos.Experience;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.Keywords;
import utilities.PageManager;

public class AddExperienceSteps {

	private PageManager pages = PageManager.getInstance();

	@Then("user tries to add a new experience")
	public void tryAddExperience(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Experience expectedExperience = Keywords.convertMapToExperience(data);

		pages.dashboardPage().initiateAddExperience();
		pages.addExperiencePage().validatePageload();
		pages.addExperiencePage().addExperience(expectedExperience);
	}

	@Then("user should see some add experience related validation errors")
	public void user_should_see_some_add_experience_related_validation_errors(DataTable dataTable) {
		List<String> expectedErrors = new ArrayList<>();

		String csErrorString = dataTable.asMap().get("errors");
		String[] csErrors = csErrorString.split(",");
		for (String error : csErrors) {
			expectedErrors.add(error.trim());
		}

		pages.addExperiencePage().errorValidation(expectedErrors);
	}

}
