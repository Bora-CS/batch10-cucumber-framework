package apiPojos;

public class User {

	public String _id;
	public String name;
	public String email;
	public String avatar;
	public String date;
	public int __v;

	public User(String _id, String name, String email, String avatar, String date, int __v) {
		this._id = _id;
		this.name = name;
		this.email = email;
		this.avatar = avatar;
		this.date = date;
		this.__v = __v;
	}

}
