package hui_automation.api_pojos;

public class Error {

	public String value;
	public String msg;
	public String param;
	public String location;

	public Error(String value, String msg, String param, String location) {
		this.value = value;
		this.msg = msg;
		this.param = param;
		this.location = location;
	}

}
