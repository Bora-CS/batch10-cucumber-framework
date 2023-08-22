package anthony;
import utilities.BoraTechAPIs;
public class API_BoraInfo {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("anth0ny@gmail.com", "PaSsWoRd123!");
		String userName = BoraTechAPIs.getAuthorizedUserMeta(token);
		System.out.println("The username is: " + userName);
		
	}

}
