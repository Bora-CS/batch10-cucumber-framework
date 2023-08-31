package api_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import apiPojos.Post;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;
import utilities.DataManager;
import utilities.Keywords;

public class PostSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] user should be able to create a new post")
	public void api_user_should_be_able_to_create_a_new_post(DataTable dataTable) {
		String expectedContent = "[" + Keywords.getTimeStamp() + "] " + dataTable.asMaps().get(0).get("content");
		String token = dataManager.getToken();
		Post post = BoraTechAPIs.createPost(token, expectedContent);
		assertEquals(expectedContent, post.text);
	}

}
