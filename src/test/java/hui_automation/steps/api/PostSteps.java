package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Post;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class PostSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Then("[API] user posts a new [Post]")
	public void api_user_posts_a_new_post(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps();
		String postContent = data.get(0).get("content") + " " + Testkeys.getTimestamp();
		Post post = BoraTechAPIs.postBoraTechPosts(dataManager.getToken(), postContent);
		dataManager.setPostAPI(post);
		assertEquals(post.text, postContent);
	}

	@Then("[API] users gets a list of posts that does not contain the previously created post")
	public void api_users_gets_a_list_of_posts_that_does_not_contain_the_previously_created_post() {
		List<Post> posts = BoraTechAPIs.getBoraTechPosts(dataManager.getToken());
		boolean targetPostFound = false;
		for (Post post : posts) {
			if (post.equals(dataManager.getPostAPI())) {
				targetPostFound = true;
				break;
			}
		}
		assertFalse(targetPostFound);
	}

}
