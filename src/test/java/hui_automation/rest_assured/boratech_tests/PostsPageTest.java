package hui_automation.rest_assured.boratech_tests;

import java.util.HashMap;
import java.util.List;

import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class PostsPageTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
			
			HashMap<String, String> post = new HashMap<String, String>();
			post.put("text", "I like fishes "+ Testkeys.getUniqueMillsTimeStr());

			String userName = BoraTechAPIs.postBoraTechPosts(token, post);
			List<HashMap<String, Object>> posts = BoraTechAPIs.getBoraTechPosts(token);
			for (HashMap<String, Object> postT : posts) {
				if (postT.get("name").equals(userName) &&postT.get("text").equals(post.get("text"))) {
					System.out.println("Passed");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
