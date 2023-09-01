package hui_automation.api_pojos;

public class Experience {

	public String _id;
	public String company;
	public String title;
	public String location;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Experience(String company, String title, String location, String from, String to, boolean current,
			String description) {
		this.title = title;
		this.company = company;
		this.location = location;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Experience) {
			Experience that = (Experience) obj;
			return this.company.equals(that.company) && this.title.equals(that.title)
					&& this.location.equals(that.location);
		}
		return false;
	}

}
