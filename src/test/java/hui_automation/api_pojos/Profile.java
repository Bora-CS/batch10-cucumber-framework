package hui_automation.api_pojos;

import java.util.List;
import java.util.Map;

public class Profile {

	public List<String> skills;
	public String _id;
	public Map<String, String> user;
	public int __v;
	public String bio;
	public String company;
	public String githubusername;
	public String location;
	public String status;
	public String website;
	public String date;
	public List<Education> education;
	public List<Experience> experience;

	public Profile(List<String> skills, String _id, Map<String, String> user, int __v, String bio, String company,
			String githubusername, String location, String status, String website, String date,
			List<Education> education, List<Experience> experience) {
		this.skills = skills;
		this._id = _id;
		this.user = user;
		this.__v = __v;
		this.bio = bio;
		this.company = company;
		this.githubusername = githubusername;
		this.location = location;
		this.status = status;
		this.website = website;
		this.date = date;
		this.education = education;
		this.experience = experience;
	}

}
