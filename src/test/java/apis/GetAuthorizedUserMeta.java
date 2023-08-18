package apis;

import utilities.BoraTechAPIs;

public class GetAuthorizedUserMeta {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		String userName = BoraTechAPIs.getAuthorizedUserMeta(token);

		System.out.println("Username: " + userName);

	}

}
