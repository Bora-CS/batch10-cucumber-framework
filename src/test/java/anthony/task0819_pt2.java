package anthony;

import io.restassured.RestAssured;

import java.util.List;

import anthony_pojo.Blog_API;
import anthony_pojo.Blog_Post;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class task0819_pt2 {
    public static void main(String[] args) {
        String endpoint = "/api/posts";
        String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");
        Blog_Post newPost = new Blog_Post("Spamming" + UTILITIES.getTimeStamp());
        RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-auth-token", token);
        
        Response response = request.post(endpoint);
        List<Blog_API> posts = response.jsonPath().getList("text", Blog_API.class);
        
        boolean check = false;
        for (Blog_API blog_API : posts) {
            if (newPost.equals(blog_API)) { // Compare with a specific Blog_API object
                check = true;
                break;
            }
        }
        
        if (check) {
            System.out.println("pass");
        } else {
            System.out.println("fail");
        }
    }
}
