package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchHomePage {

	private WebDriver driver;
	
	@FindBy(xpath = "//textarea[@name='q']")
	private WebElement googleSearchBox;

	@FindBy(xpath = "//input[@name='btnI'][@role='button']/preceding-sibling::input[@name='btnK']")
	private WebElement googleSearchButton;

	public SearchHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterSearchTerm(String searchTerm) {
		googleSearchBox.sendKeys(searchTerm);
	}

	public void clickSearchButton() {
		googleSearchButton.click();
	}

}
