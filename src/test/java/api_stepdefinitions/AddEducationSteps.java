package api_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import apiPojos.ApiError;
import apiPojos.Education;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;
import utilities.DataManager;

public class AddEducationSteps {

	private DataManager dataManager = DataManager.getInstance();

	@When("[API] user tries to add a new education")
	public void api_user_tries_to_add_a_new_education(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Education education = new Education(data.get("school"), data.get("degree"), data.get("fieldofstudy"),
				data.get("from"), data.get("to"), Boolean.valueOf(data.get("current")), data.get("description"));

		List<ApiError> errors = BoraTechAPIs.addEducationUnhappy(dataManager.getToken(), education);
		dataManager.setApiErrors(errors);
	}

	@Then("[API] user should receive missing form errors")
	public void api_user_should_receive_error_messages(DataTable dataTable) {
		// Prepare the maps to compare
		List<String> expectedErrors = new ArrayList<>();
		List<String> actualErrors = new ArrayList<>();

		// Convert expected errors
		String csErrorString = dataTable.asMap().get("errors");
		String[] csErrors = csErrorString.split(",");
		for (String error : csErrors) {
			expectedErrors.add(error.trim());
		}

		// Convert actual errors
		List<ApiError> apiErrors = dataManager.getApiErrors();
		for (ApiError apiError : apiErrors) {
			actualErrors.add(apiError.msg);
		}

		// Asserting the expected errors with actual errors
		Collections.sort(expectedErrors);
		Collections.sort(actualErrors);
		assertEquals(expectedErrors, actualErrors);
	}

}
