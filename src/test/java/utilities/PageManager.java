package utilities;

import org.openqa.selenium.WebDriver;

import page_objects.DashboardPage;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.Navbar;
import page_objects.PostsPage;

public class PageManager {

	private static PageManager pageManager = null;
	private WebDriver driver;

	private HomePage homePage;
	private LoginPage loginPage;
	private DashboardPage dashboardPage;
	private Navbar navbar;
	private PostsPage postsPage;

	private PageManager(WebDriver driver) {
		this.driver = driver;
	}

	public static PageManager getInstance() {
		if (pageManager == null) {
			pageManager = new PageManager(DriverManager.getInstance());
		}
		return pageManager;
	}

	public static void cleanup() {
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

}
