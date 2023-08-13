package pojo;

public class Experience {

	public String jobTitle;
	public String company;
	public String location;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Experience(String jobTitle, String company, String location, String from, String to, boolean current,
			String description) {
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}

}
