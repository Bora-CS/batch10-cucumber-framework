package lydia.POJO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			String description, String[]expectedErrors) {
		this.jobTitle = jobTitle;
		this.company = company;
		this.location = location;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
		if(expectedErrors == null) {
			this.expectedErrors = new ArrayList<>();
		}else {
			this.expectedErrors = Arrays.asList(expectedErrors);
		}
		this.isHappyPath = this.expectedErrors.size()==0;
	}

}


