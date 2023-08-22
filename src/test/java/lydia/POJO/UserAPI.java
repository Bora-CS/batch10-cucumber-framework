package lydia.POJO;

public class UserAPI {

		public String _id;
		public String name;
		public String text;
		public String avatar;
		public String date;
		public int __v;

		public UserAPI(String _id, String name, String text, String avatar, String date, int __v) {
			this._id = _id;
			this.name = name;
			this.text = text;
			this.avatar = avatar;
			this.date = date;
			this.__v = __v;
		}
		
		public String toString() {
			return "id: " + this._id + "\nname: " + this.name + "\ntext: " + this.text + "\navatar: " + this.avatar + "\ndate: " + this.date + "\n-v: " + this.__v;
		}

	}

