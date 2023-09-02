package apiPojos;

public class ApiError {

	public String value;
	public String msg;
	public String param;
	public String location;

	public ApiError(String value, String msg, String param, String location) {
		this.value = value;
		this.msg = msg;
		this.param = param;
		this.location = location;
	}

}
