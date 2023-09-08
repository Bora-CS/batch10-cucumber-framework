package hui_automation.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Education {

	public String school;
	public String degree;
	public String fieldofstudy;
	public String fromDate;
	public String toDate;
	public boolean current;
	public String programDescription;
	public List<String> errorTexts;
	public boolean isTestPositive;

	public Education(Map<String, String> data) {
		this.errorTexts = new ArrayList<>();
		for (Entry<String, String> entry : data.entrySet()) {
			String value = entry.getValue();
			if (value == null)
				value = "";
			switch (entry.getKey().toLowerCase()) {
			case "school":
				this.school = value;
				break;
			case "degree":
				this.degree = value;
				break;
			case "fieldofstudy":
			case "field of study":
				this.fieldofstudy = value;
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
			case "programdescription":
			case "program description":
				this.programDescription = value;
				break;
			case "error":
			case "errors":
				if (value.isEmpty())
					break;
				this.errorTexts = Arrays.asList(value.split(","));
				break;
			default:
				break;
			}
		}
		this.isTestPositive = this.errorTexts.size() == 0;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("school", this.school);
		data.put("degree", this.degree);
		data.put("fieldofstudy", this.fieldofstudy);
		data.put("from", this.fromDate.replace("/", "-"));
		if (this.current)
			data.put("to", "");
		else
			data.put("to", this.toDate.replace("/", "-"));
		data.put("current", this.current);
		data.put("description", this.programDescription);
		return data;
	}

}
