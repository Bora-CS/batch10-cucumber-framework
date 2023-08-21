package hui_automation.api_pojos;

public class Education {

	public String _id;
	public String school;
	public String degree;
	public String fieldofstudy;
	public String from;
	public String to;
	public boolean current;
	public String description;

	public Education(String school, String degree, String fieldofstudy, String from, String to, boolean current,
			String description, String[] expectedErrors) {
		this.school = school;
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Education) {
			Education that = (Education) obj;
			return this.school.equals(that.school) && this.degree.equals(that.degree)
					&& this.fieldofstudy.equals(that.fieldofstudy);
		}
		return false;
	}

}
