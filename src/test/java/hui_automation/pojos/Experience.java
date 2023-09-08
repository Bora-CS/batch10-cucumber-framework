package hui_automation.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Experience {

	public String company;
	public String jobTitle;
	public String location;
	public String fromDate;
	public String toDate;
	public boolean current;
	public String jobDescription;
	public List<String> ErrorMessages;
	public boolean isTestPositive;

	public Experience(String company, String jobTitle, String location, String fromDate, String toDate, boolean current,
			String jobDescription, String[] ErrorMessages) {
		this.company = company;
		if (this.company == null)
			this.company = "";
		
		this.jobTitle = jobTitle;
		if (this.jobTitle == null)
			this.jobTitle = "";

		this.location = location;
		if (this.location == null)
			this.location = "";

		this.fromDate = fromDate;
		if (this.fromDate == null)
			this.fromDate = "";

		this.toDate = toDate;
		if (this.toDate == null)
			this.toDate = "";

		this.current = current;
		this.jobDescription = jobDescription;
		if (this.jobDescription == null)
			this.jobDescription = "";

		if (ErrorMessages == null)
			this.ErrorMessages = new ArrayList<String>();
		else
			this.ErrorMessages = Arrays.asList(ErrorMessages);
		this.isTestPositive = this.ErrorMessages.size() == 0;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> expMap = new HashMap<>();
		expMap.put("company", this.company);
		expMap.put("title", this.jobTitle);
		expMap.put("location", this.location);
		expMap.put("from", this.fromDate.replace("/", "-"));
		if (this.current)
			expMap.put("to", "");
		else
			expMap.put("to", this.toDate.replace("/", "-"));
		expMap.put("current", this.current);
		expMap.put("description", this.jobDescription);
		return expMap;
	}

}
