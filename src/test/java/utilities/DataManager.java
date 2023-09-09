package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import apiPojos.ApiError;
import apiPojos.Post;

public class DataManager {

	private static DataManager dataManager = null;

	private String token;
	private List<ApiError> apiErrors;
	private String errorMessage;
	private Post post;

	private DataManager() {
	}

	public static DataManager getInstance() {
		if (dataManager == null) {
			dataManager = new DataManager();
		}
		return dataManager;
	}

	public static void cleanup() {
		dataManager = null;
	}

	public String getToken() {
		assertNotNull(token, "DataManager - Token is not available");
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<ApiError> getApiErrors() {
		assertNotNull(apiErrors);
		return apiErrors;
	}

	public void setApiErrors(List<ApiError> apiErrors) {
		this.apiErrors = apiErrors;
	}

	public String getErrorMessage() {
		assertNotNull(errorMessage);
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Post getPost() {
		assertNotNull(post);
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
