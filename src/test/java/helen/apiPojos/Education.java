package helen.apiPojos;

public class Education {
	

	public String school; 
	public String degree; 
	public String fieldofstudy; //all lower case
	public String from; 
	public String to; 
	public boolean current; 
	public String description; 
	
	//constructor
	public Education(String school, String degree, String fieldofstudy, String from, String to, 
			boolean current, String description) { 
		this.school = school;
		this.degree = degree;
		this.fieldofstudy = fieldofstudy;
		this.from = from;
		this.to = to;
		this.current = current;
		this.description = description;
	
	}
	
	//override the equals function from object class
	public boolean equals(Education education) {
		return (this.degree.equals(education.degree) && this.school.equals(education.school)
				&& this.fieldofstudy.equals(education.fieldofstudy) && this.description.equals(education.description));
	}
	
	
}

//compare two Education objects 