package apiPojos;

import utilities.Keywords;

public class PostBody {

	public String text;

	public PostBody(String text, boolean makeUnique) {
		this.text = text + (makeUnique ? Keywords.getTimeStamp() : "");
	}

}
