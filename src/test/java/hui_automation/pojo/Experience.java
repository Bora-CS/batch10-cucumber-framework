package hui_automation.pojo;

public class Experience {

	public String expCompany = "";
	public String expTitle = "";
	public String location = "";
	public String expStartDate = "";
	public String expEndDate = "";
	public boolean current;
	public String description = "";

	public Experience(String expCompany, String expTitle, String location, String expStartDate, String expEndDate,
			boolean current, String description) {
		this.expCompany = expCompany;
		this.expTitle = expTitle;
		this.location = location;
		this.expStartDate = expStartDate;
		this.expEndDate = expEndDate;
		this.current = current;
		this.description = description;
	}

}
