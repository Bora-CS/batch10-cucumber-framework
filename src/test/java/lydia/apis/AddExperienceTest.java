package lydia.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lydia.POJO.ExperienceAPI;
import lydia.utilities.BoratechAPIs;

public class AddExperienceTest {

public static void main(String[] args) {

			String token = BoratechAPIs.Login("w.lydia.liu@gmail.com", "Liu123456");
			String endpoint = "/api/profile/experience";
			
			//WAY3:POJO
			//ExperienceAPI body = new ExperienceAPI("");
			
			//Way1:MAP
			Map<String, Object> body = new HashMap<>();
			
			body.put("title", "Automation test engineer");
			body.put("company", "BoraTech");
			body.put("location","Annandale VA");
					
			
			//WAY2:JsonObject
			
			//JsonObject body = new JsonObject();
			//body.addProperty("school", "BoraTech");
			//body.addProperty("", "");
			
			RequestSpecification request = RestAssured.given();
			
			request.header("x-auth-token",token);
			request.header("Content-Type", "application/json");
			
			request.body(body);//give the body
			Response response = request.put(endpoint);
			
		
			
			System.out.println(response.body().asPrettyString());
				
			}

}
