package pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Education {

	public String school;
	public String degree;
	public String fieldOfStudy;
	public String from;
	public String to;
	public boolean current;
	public String description;
	public List<String> expectedErrors;
	public boolean isHappyPath;

	public Education(String school, String degree, String fieldOfStudy, String from, String to, boolean current,
			String description, String[] expectedErrors) {
		this.school = school;
		this.degree = degree;
		this.fieldOfStudy = fieldOfStudy;
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

}
