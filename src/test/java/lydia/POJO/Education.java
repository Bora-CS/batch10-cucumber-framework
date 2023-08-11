package lydia.POJO;

public class Education {

		public String school;
		public String degree;
		public String fieldOfStudy;
		public String from;
		public String to;
		public boolean current;
		public String description;

		//constructor:
		public Education(String school, String degree, String fieldOfStudy, String from, boolean current,String to,
				String description) {
			this.school = school;
			this.degree = degree;
			this.fieldOfStudy = fieldOfStudy;
			this.from = from;
			this.current = current;
			this.to = to;
			this.description = description;
		}

	}

