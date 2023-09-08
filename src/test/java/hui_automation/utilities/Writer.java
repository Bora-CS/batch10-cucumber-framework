package hui_automation.utilities;

public class Writer {

	public static void main(String[] args) {
		Configuration.config("browser", "chrome");
		System.out.println(Configuration.get("browser"));
	}

}
