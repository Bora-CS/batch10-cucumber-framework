package helen.apiPojos;

import java.util.ArrayList;

public class NewPost {

	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;
	public ArrayList<Object> likes;
	public ArrayList<Object> comments;
	public String date;
	public int __v;

	

	public NewPost(String _id, String text, String name, String avatar, String user, ArrayList<Object> likes,
			ArrayList<Object> comments, String date, int __v) {
		this._id = _id;
		this.text = text;
		this.name = name;
		this.avatar = avatar;
		this.user = user;
		this.likes = likes;
		this.comments = comments;
		this.date = date;
		this.__v = __v;
	}

	
	public boolean equals(NewPost newPost) {  
		return this.text.equals(newPost.text) && this.name.equals(newPost.name) ;
	}
	

}
