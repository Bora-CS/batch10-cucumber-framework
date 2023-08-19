package ardal_practice.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Experience {
	public String company;
	public String title;
	public String location;
	public String from;
	public String to;
	public boolean current;
	public String description;
	public List<String> alerts;
	public boolean isHappyPath;

	public Experience(String company, String title, String location, String from, String to, boolean current,
			String description, String[] alerts) {
		this.company = company;
		this.title = title;
		this.location = location;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
		if (alerts == null)
			this.alerts = new ArrayList<String>();
		else
			this.alerts = Arrays.asList(alerts);
		this.isHappyPath = this.alerts.size() == 0;
	}

}
