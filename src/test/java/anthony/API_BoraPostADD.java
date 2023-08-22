package anthony;

import anthony_pojo.Blog_Post;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class API_BoraPostADD {
    public static void main(String[] args) {
        String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");

        RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("x-auth-token", token);

        Blog_Post post = new Blog_Post("Spamming" + UTILITIES.getTimeStamp());

        request.body(post);

        io.restassured.response.Response postResponse = request.post("/api/posts");
        

        System.out.println("Status code: " + postResponse.getStatusCode());
        System.out.println(postResponse.getBody().asPrettyString());
        
    }
}
