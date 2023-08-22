package anthony;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import anthony_pojo.Blog_API;
import anthony_pojo.Blog_Post;

public class API_BoraPostVALIDATE {
    public static void main(String[] args) {
    	String yourPost = "Spamming";
        String endpoint = "/api/posts";
        String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");
        RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-auth-token", token);
        
        Blog_Post newPost = new Blog_Post(yourPost + UTILITIES.getTimeStamp());

        Response response = request.body(newPost).post(endpoint);

        String expectedText = newPost.getText();
        String actualText = response.getBody().as(Blog_API.class).getText();

        validateResponse(expectedText, actualText);
    }

    public static void validateResponse(String expectedText, String actualText) {
        if (expectedText.equals(actualText)) {
            System.out.println("We see your post");
        } else {
            System.out.println("FAILLLLL");
        }
    }
}
