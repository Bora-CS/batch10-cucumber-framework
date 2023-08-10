package hui_automation.pojo;

public class Education {

	public String expSchool = "";
	public String expDegree = "";
	public String fieldofstudy = "";
	public String expStartDate = "";
	public String expEndDate = "";
	public boolean current;
	public String description = "";

	public Education(String expSchool, String expDegree, String fieldofstudy, String expStartDate, String expEndDate,
			boolean current, String description) {
		this.expSchool = expSchool;
		this.expDegree = expDegree;
		this.fieldofstudy = fieldofstudy;
		this.expStartDate = expStartDate;
		this.expEndDate = expEndDate;
		this.current = current;
		this.description = description;
	}

}
