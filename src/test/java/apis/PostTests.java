package apis;

import java.util.List;
import apiPojos.Post;
import utilities.BoraTechAPIs;
import utilities.Keywords;

public class PostTests {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		String expectedText = "Murad Test" + Keywords.getTimeStamp();
		Post resultPost = BoraTechAPIs.createPost(token, expectedText);

		if (!expectedText.equals(resultPost.text)) {
			System.out.println("Failed");
			System.out.println("Expected: " + expectedText);
			System.out.println("Actual: " + resultPost.text);
		}

		List<Post> posts = BoraTechAPIs.getPosts(token);
		boolean found = false;
		for (Post post : posts) {
			if (post.text.equals(expectedText) && post.name.equals(resultPost.name)) {
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Passed");
		} else {
			System.out.println("Failed, newly created post was not found.");
		}

	}

}
