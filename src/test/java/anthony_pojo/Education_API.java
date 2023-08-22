package anthony_pojo;

public class Education_API {

	public String school;
	public String degree;
	public String fieldofstudy;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Education_API(String school, String degree, String fieldofstudy, String from, String to, boolean current,
			String description) {
		this.school = school;
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}

	public boolean equals(Education education) {
		return this.degree.equals(education.degree) && this.school.equals(education.school)
				&& this.fieldofstudy.equals(education.fieldOfStudy) && this.description.equals(education.description);
	}
}
