package hui_automation.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Experience {

	public String company;
	public String title;
	public String location;
	public String startDate;
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

}
