package helen.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class Experience {

	public String jobTitle;
	public String company;
	public String location;
	public String from;
	public String to;
	public boolean current;
	public String description;
	public List<String> expectedErrors;
	public boolean isHappyPath;

	public Experience(String jobTitle, String company, String location, String from, String to, boolean current,
			String description, String[] expectedErrors) {
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
		if (expectedErrors == null) {
			this.expectedErrors = new ArrayList<>();
		} else {
			this.expectedErrors = Arrays.asList(expectedErrors);
		}
		this.isHappyPath = this.expectedErrors.size() == 0;
	}
	
//	
//	public Experience(String jobTitle, String company, String from) {
//		this.jobTitle = jobTitle;
//		this.company = company;
//		this.from = from;
//	}



	public String pojoToJason() {
		return new Gson().toJson(toHashMap());
	}
	
	private HashMap<String, Object> toHashMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("jobTitle", this.jobTitle);
		map.put("company", this.company);
		map.put("location", this.location);
		map.put("from", this.from);
		map.put("to", this.to);
		map.put("current", this.current);
		map.put("description",  this.description);
		
		return map;
		
	}
	

}
