package hui_automation.api_pojos;

import java.util.ArrayList;

public class Post {

	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;
	public ArrayList<Like> likes;
	public ArrayList<Comment> comments;
	public String date;
	public int __v;

	public Post(String _id, String text, String name, String avatar, String user, ArrayList<Like> likes,
			ArrayList<Comment> comments, String date, int __v) {
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

	public boolean equals(Object obj) {
		if (obj instanceof Post) {
			Post that = (Post) obj;
			return this.user.equals(that.user) && this.name.equals(that.name) && this.text.equals(that.text);
		}
		return false;
	}

}
