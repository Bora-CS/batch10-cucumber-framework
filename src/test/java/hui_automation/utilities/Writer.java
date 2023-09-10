package hui_automation.utilities;

public class Writer {

	public static void main(String[] args) {
		setupChrome();
	}

	private static void setupChrome() {
		Configuration.config("browser", "chrome");
		System.out.println(Configuration.get("browser"));
	}

}
