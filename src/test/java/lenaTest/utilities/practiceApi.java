package lenaTest.utilities;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lenaTest.api.lenaUApi;

public class practiceApi {

	public static void main(String[] args) throws Exception {
		String token = lenaTest_uti.logging("chenlena@outlook.com", "798911");
		lenaUApi.addPost(token);
	}

}
