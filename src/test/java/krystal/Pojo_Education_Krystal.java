package krystal;

public class Pojo_Education_Krystal {

	public String school;
	public String degree;
	public String fieldStudy;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Pojo_Education_Krystal(String school, String degree, String fieldOfStudy, String from, String to, boolean current,
			String description) {
		this.school = school;
		this.degree = degree;
		this.fieldStudy = fieldOfStudy;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}

}
