package apiPojos;

import utilities.Keywords;

public class PostRequest {

	public String text;

	public PostRequest(String text, boolean makeUnique) {
		this.text = text + (makeUnique ? Keywords.getTimeStamp() : "");
	}

}
