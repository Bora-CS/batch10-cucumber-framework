package hui_automation.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Education {

	public String school;
	public String degree;
	public String fieldofstudy;
	public String fromDate;
	public String toDate;
	public boolean current;
	public String programDescription;
	public List<String> ErrorMessages;
	public boolean isTestPositive;

	public Education(String school, String degree, String fieldofstudy, String fromDate, String toDate, boolean current,
			String programDescription, String[] ErrorMessages) {
		this.school = school;
		if (this.school == null)
			this.school = "";

		this.degree = degree;
		if (this.degree == null)
			this.degree = "";

		this.fieldofstudy = fieldofstudy;
		if (this.fieldofstudy == null)
			this.fieldofstudy = "";

		this.fromDate = fromDate;
		if (this.fromDate == null)
			this.fromDate = "";

		this.toDate = toDate;
		if (this.toDate == null)
			this.toDate = "";

		this.current = current;
		this.programDescription = programDescription;
		if (this.programDescription == null)
			this.programDescription = "";

		if (ErrorMessages == null)
			this.ErrorMessages = new ArrayList<String>();
		else
			this.ErrorMessages = Arrays.asList(ErrorMessages);
		this.isTestPositive = this.ErrorMessages.size() == 0;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> eduMap = new HashMap<>();
		eduMap.put("school", this.school);
		eduMap.put("degree", this.degree);
		eduMap.put("fieldofstudy", this.fieldofstudy);
		eduMap.put("from", this.fromDate.replace("/", "-"));
		if (this.current)
			eduMap.put("to", "");
		else
			eduMap.put("to", this.toDate.replace("/", "-"));
		eduMap.put("current", this.current);
		eduMap.put("description", this.programDescription);
		return eduMap;
	}

}
