package hui_automation.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Education {

	public String school;
	public String degree;
	public String fieldofstudy;
	public String startDate;
	public String endDate;
	public boolean current;
	public String description;
	public List<String> messages;
	public boolean isTestPositive;

	public Education(String school, String degree, String fieldofstudy, String startDate, String endDate,
			boolean current, String description, String[] messages) {
		this.school = school;
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.current = current;
		this.description = description;
		if (messages == null)
			this.messages = new ArrayList<String>();
		else
			this.messages = Arrays.asList(messages);
		this.isTestPositive = this.messages.size() == 0;
	}

	public HashMap<String, Object> toHashMap() {
		HashMap<String, Object> eduMap = new HashMap<>();
		eduMap.put("school", this.school);
		eduMap.put("degree", this.degree);
		eduMap.put("fieldofstudy", this.fieldofstudy);
		eduMap.put("from", this.startDate.replace("/", "-"));
		if (this.current)
			eduMap.put("to", "");
		else
			eduMap.put("to", this.endDate.replace("/", "-"));
		eduMap.put("current", this.current);
		eduMap.put("description", this.description);
		return eduMap;
	}

}
