package helen.apis;

public class CreateNewPost {


	public static void main(String[] args) {

		//login and get a token
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		
		String message = "Helloooo-" + helen.utilities.Keywords.getTimeStamp();
		
		//create new post
		String actualText = helen.utilities.BoraTechApis.addNewPost(token, message).toString();
		
		//validate
		if (actualText.equals(message)) {
			System.out.println("Pass. \nNew Post added: " + actualText);
		} else {
			System.out.println("Fail. \nExpected: " + message + "\nActual  : " + actualText);
			}
		}
				

}

