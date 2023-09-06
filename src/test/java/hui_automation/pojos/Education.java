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
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.current = current;
		this.programDescription = programDescription;
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
