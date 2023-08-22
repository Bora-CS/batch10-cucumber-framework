package anthony_pojo;

public class Education {
	public String school;
	public String degree;
	public String fieldOfStudy;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Education(String school, String degree, String fieldOfStudy, String from, String to, boolean current, String description) {

		this.school = school;
		this.degree = degree;
		this.fieldOfStudy = fieldOfStudy;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}
}