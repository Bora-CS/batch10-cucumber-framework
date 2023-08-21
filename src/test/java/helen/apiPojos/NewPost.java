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

	
	public NewPost(String _id, String text, String name, String avatar,String user,
			ArrayList<Object> likes, ArrayList<Object> comments, String date, int __v) {
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
	
	public NewPost(String text) {
		this.text = text;
	}
	

	//validate for text and name only for CreateNewPost
	public boolean equals1(NewPost newPost) {  
		return this.text.equals(newPost.text) && this.name.equals(newPost.name);
	}
	
	//validate for every single element in json object for GetPostPageMeta
	public boolean equals2(NewPost newPost) {
		return 	this._id.equals(newPost._id) && this.text.equals(newPost.text) && this.name.equals(newPost.name)
				&& this.avatar.equals(newPost.avatar) && this.user.equals(newPost.avatar) && this.likes.equals(newPost.likes)
				&& this.comments.equals(newPost.comments) && this.date.equals(newPost.date) && this.__v == newPost.__v;
	}
	
	
}




		




//String _id, String text, String name, String avatar,String user, ArrayList<Object> likes, ArrayList<Object> comments, String date, int __v 	

