package hui_automation.print_html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hui_automation.Testkeys;
import hui_automation.boratech_tests.BoraTech;

public class PrintFile {

	public static void main(String[] args) {
		WebDriver printDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			printDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			printDriver.manage().window().maximize();
			BoraTech.login(printDriver, email, password);
			Testkeys.pause(3);

			printDriver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			printDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.pause(1);
			String pageSource = printDriver.getPageSource();
			
			findFile("Experience_Negative.html", pageSource);
			System.out.println("You got it, Boss!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Testkeys.terminate(printDriver);
		}
	}

	private static void findFile(String fileName, String fileData) throws Exception {
		String folderPathStr = "./src/test/java/hui_automation/print_html/target_html";
		String targetPathStr = folderPathStr + "/" + fileName;
		File targetFileObj = new File(targetPathStr);
		FileWriter fw = new FileWriter(targetFileObj);
		BufferedWriter bw = new BufferedWriter(fw);
		Document doc = Jsoup.parse(fileData);
		bw.write(doc.toString());
		bw.close();
	}

}
