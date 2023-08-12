package hui_automation.pojo;

public class Education {

	public String school;
	public String degree;
	public String fieldofstudy;
	public String startDate;
	public String endDate;
	public boolean current;
	public String description;

	public Education(String school, String degree, String fieldofstudy, String startDate, String endDate,
			boolean current, String description) {
		this.school = school;
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.current = current;
		this.description = description;
	}

}
