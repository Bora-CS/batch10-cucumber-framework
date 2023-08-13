package anthony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.Keywords;

public class minimal_test {

    public static void main(String[] args) {
        String email = "demo@minimals.cc";
        String password = "demo1234";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://minimals.cc/auth/amplify/login?returnTo=%2Fdashboard");
            Keywords.wait(5);
            driver.findElement(By.xpath("//input[@name ='email']")).sendKeys(email);
            Keywords.wait(1);
            driver.findElement(By.xpath("//input[@name ='password']")).sendKeys(password);
            Keywords.wait(4);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Keywords.wait(6);
            driver.findElement(By.xpath("//span[text()='user']")).click();
            driver.findElement(By.xpath("//a[@href='/dashboard/user/list']")).click();
            Keywords.wait(5);

            WebElement roleDropdown = driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]//span[text()='Role']/../../.."));
            roleDropdown.click();
            driver.findElement(By.xpath("//li[@data-value='HR Manager']")).click();
            driver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();
            driver.findElement(By.xpath("//li[@data-value='Software Developer']")).click();

            String[] rolesToValidate = { "HR Manager", "Data Analyst", "Software Developer" };

            boolean allRolesSelected = true;

            for (String role : rolesToValidate) {
                boolean roleSelected = false;

                if (driver.findElements(By.xpath("//li[text()='" + role + "']")).size() > 0) {
                    roleSelected = true;
                }

                if (!roleSelected) {
                    allRolesSelected = false;
                    System.out.println(role + " the role is not in the dropdown");
                }
            }

            if (allRolesSelected) {
                System.out.println( "All roles are displayed!!!!");
            } else {
                System.out.println("Roles selection validation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!!!: " + e.getMessage());
        } finally {
            driver.close();
            driver.quit();
        }
    }
}

