package helen.apis;

public class GetAuthorizationUserMeta {

	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		//System.out.println("token");
		
//		String userName = helen.utilities.BoraTechApis.getAuthorizedUserMeta(token);
//		System.out.println("Username: " + userName);

		String email = helen.utilities.BoraTechApis.getAuthorizedUserMeta(token);
		System.out.println("Email: " + email);
		
		
	}

}


