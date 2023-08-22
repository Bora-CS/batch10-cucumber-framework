package anthony_pojo;

import java.util.ArrayList;

public class Blog_API {
	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;
	public ArrayList<Object> likes;
	public ArrayList<Object> comments;
	public String date;
	public int __v;

	public Blog_API(String _id, String text, String name, String avatar, String user, ArrayList<Object> likes,
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

	public boolean equals(Object objects) {
		if (objects instanceof Blog_API) {
			Blog_API that = (Blog_API) objects;
			return this.name.equals(that.name) && this.text.equals(that.text);
		}
		return false;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}
}
