package api_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

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
		dataManager.setPost(post);
	}

	@Then("[API] user can validate that the post they created previously is not there anymore")
	public void api_user_can_validate_that_the_post_they_created_previously_is_not_there_anymore() {
		String token = dataManager.getToken();
		Post previouslyCreatedPost = dataManager.getPost();
		List<Post> posts = BoraTechAPIs.getPosts(token);

		boolean found = false;
		for (Post post : posts) {
			if (post.equals(previouslyCreatedPost)) {
				found = true;
				break;
			}
		}

		assertFalse(found, "Previously deleted post is still there");
	}

}
