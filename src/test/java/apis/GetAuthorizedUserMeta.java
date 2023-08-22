package apis;

import apiPojos.User;
import utilities.BoraTechAPIs;

public class GetAuthorizedUserMeta {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		User user = BoraTechAPIs.getAuthorizedUserMeta(token);

		System.out.println(user.email);

	}

}
