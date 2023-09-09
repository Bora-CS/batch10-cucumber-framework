package hui_automation.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Experience {

	public String company;
	public String jobTitle;
	public String location;
	public String fromDate;
	public String toDate;
	public boolean current;
	public String jobDescription;
	public List<String> errorTexts;
	public boolean isTestPositive;

	public Experience(Map<String, String> data) {
		this.company = "";
		this.jobTitle = "";
		this.location = "";
		this.fromDate = "";
		this.toDate = "";
		this.jobDescription = "";
		this.errorTexts = new ArrayList<>();
		for (Entry<String, String> entry : data.entrySet()) {
			String value = entry.getValue();
			if (value == null)
				value = "";
			switch (entry.getKey().toLowerCase()) {
			case "company":
				this.company = value;
				break;
			case "title":
			case "jobtitle":
			case "job title":
				this.jobTitle = value;
				break;
			case "location":
				this.location = value;
				break;
			case "from":
			case "fromdate":
			case "from date":
				this.fromDate = value;
				break;
			case "to":
			case "todate":
			case "to date":
				this.toDate = value;
				break;
			case "current":
				this.current = Boolean.valueOf(value);
				break;
			case "description":
			case "jobdescription":
			case "job description":
				this.jobDescription = value;
				break;
			case "error":
			case "errors":
				if (value.isEmpty())
					break;
				String[] errors = value.split(",");
				for (String error : errors)
					this.errorTexts.add(error.trim());
				break;
			default:
				break;
			}
		}
		this.isTestPositive = this.errorTexts.size() == 0;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("company", this.company);
		data.put("title", this.jobTitle);
		data.put("location", this.location);
		data.put("from", this.fromDate.replace("/", "-"));
		if (this.current)
			data.put("to", "");
		else
			data.put("to", this.toDate.replace("/", "-"));
		data.put("current", this.current);
		data.put("description", this.jobDescription);
		return data;
	}

}
