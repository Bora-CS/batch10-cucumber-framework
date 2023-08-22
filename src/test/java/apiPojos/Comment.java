package apiPojos;

public class Comment {

	public String date;
	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;

	public Comment(String date, String _id, String text, String name, String avatar, String user) {
		this.date = date;
		this._id = _id;
		this.text = text;
		this.name = name;
		this.avatar = avatar;
		this.user = user;
	}

}
