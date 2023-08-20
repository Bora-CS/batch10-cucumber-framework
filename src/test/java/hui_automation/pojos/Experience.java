package hui_automation.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Experience {

	public String company;
	public String title;
	public String location;
	public String startDate; // uuuu/MM/dd
	public String endDate;
	public boolean current;
	public String description;
	public List<String> messages;
	public boolean isTestPositive;

	public Experience(String company, String title, String location, String startDate, String endDate, boolean current,
			String description, String[] messages) {
		this.company = company;
		this.title = title;
		this.location = location;
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
		HashMap<String, Object> expMap = new HashMap<>();
		expMap.put("company", this.company);
		expMap.put("title", this.title);
		expMap.put("location", this.location);
		expMap.put("from", this.startDate.replace("/", "-"));
		if (this.current)
			expMap.put("to", "");
		else
			expMap.put("to", this.endDate.replace("/", "-"));
		expMap.put("current", this.current);
		expMap.put("description", this.description);
		return expMap;
	}

}
