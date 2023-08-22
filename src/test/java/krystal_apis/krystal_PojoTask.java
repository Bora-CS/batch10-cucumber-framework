package krystal_apis;

public class krystal_PojoTask {

	public String _id;
	public String text;
	public String name;
	public String avatar;
	public String user;
	public String likes;
	public String comments;
	public String date;
	public String __v;

	public krystal_PojoTask(String _id, String text, String name, String avatar, String user, String likes,
			String comments, String date, String __v) {
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

	public boolean equals(krystal_PojoTask that) {
		return this.name.equals(that.name) && this.user.equals(that.user) && 
				this.comments.equals(that.comments);
	}
}