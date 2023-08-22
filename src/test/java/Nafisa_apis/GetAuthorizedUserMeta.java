package Nafisa_apis;

import apiPojos.User;
import utilities.BoraTechAPIs;

public class GetAuthorizedUserMeta {

	public static void main(String[] args) {
		


				String token = BoraTechAPIs.login("nanfeise@gmail.com", "Nafisa1996");

				User user = BoraTechAPIs.getAuthorizedUserMeta(token);

				System.out.println(user.email);

			}

		

	

}
