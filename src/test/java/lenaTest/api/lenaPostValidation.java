package lenaTest.api;

public class lenaPostValidation {

	public static void main(String[] args) throws Exception {
		String token = lenaUApi.login("chenlena@outlook.com", "798911");
		lenaUApi.addPost(token);
		System.out.println(lenaUApi.getPost(token));

	}
}
