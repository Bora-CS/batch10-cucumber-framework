package apiPojos;

public class Post {

	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;
	public Like[] likes;
	public Comment[] comments;
	public String date;
	public int __v;

	public Post(String _id, String text, String name, String avatar, String user, Like[] likes, Comment[] comments,
			String date, int __v) {
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

}
