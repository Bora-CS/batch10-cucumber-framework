package apiPojos;

public class Experience {

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

	public boolean equals(Experience that) {
		return this.company.equals(that.company) && this.title.equals(that.title) && this.location.equals(that.location)
				&& this.description.equals(that.description);
	}

}
