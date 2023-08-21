package hui_automation.rest_assured.boratech_tests;

import java.util.List;

import hui_automation.api_pojos.Post;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class PostsPageTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);

			String postContent = "I want to fight a fish. " + Testkeys.getUniqueMillsTimeStr();
			Post expectedPost = BoraTechAPIs.postBoraTechPosts(token, postContent);

			List<Post> allPosts = BoraTechAPIs.getBoraTechPosts(token);
			boolean targetPost = false;
			for (Post actualPost : allPosts) {
				if (actualPost.equals(expectedPost)) {
					targetPost = true;
					System.out.println(actualPost.name + ": " + actualPost.text);
					break;
				}
			}

			if (targetPost)
				System.out.println("Test passed.");
			else
				System.out.println("Test failed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
