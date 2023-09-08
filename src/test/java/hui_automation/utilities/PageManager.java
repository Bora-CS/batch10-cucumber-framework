package hui_automation.utilities;

import org.openqa.selenium.WebDriver;

import hui_automation.pages.bora_tech.*;

public class PageManager {

	private static PageManager pageManager = null;
	private WebDriver driver;

	private Navbar navbar;
	private HomePage homePage;
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private PostsPage postsPage;
	private AddExperiencePage addExperiencePage;
	private AddEducationPage addEducationPage;

	private PageManager(WebDriver driver) {
		this.driver = driver;
	}

	public static PageManager getInstance() {
		if (pageManager == null) {
			pageManager = new PageManager(DriverManager.getInstance());
		}
		return pageManager;
	}

	public static void reset() {
		pageManager = null;
	}

	public HomePage homePage() {
		if (homePage == null) {
			homePage = new HomePage(driver);
		}
		return homePage;
	}

	public LoginPage loginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		}
		return loginPage;
	}

	public DashboardPage dashboardPage() {
		if (dashboardPage == null) {
			dashboardPage = new DashboardPage(driver);
		}
		return dashboardPage;
	}

	public Navbar navbar() {
		if (navbar == null) {
			navbar = new Navbar(driver);
		}
		return navbar;
	}

	public PostsPage postsPage() {
		if (postsPage == null) {
			postsPage = new PostsPage(driver);
		}
		return postsPage;
	}

	public AddExperiencePage addExperiencePage() {
		if (addExperiencePage == null) {
			addExperiencePage = new AddExperiencePage(driver);
		}
		return addExperiencePage;
	}

	public AddEducationPage addEducationPage() {
		if (addEducationPage == null) {
			addEducationPage = new AddEducationPage(driver);
		}
		return addEducationPage;
	}

}
