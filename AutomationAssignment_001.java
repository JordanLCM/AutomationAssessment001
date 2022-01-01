package AutomationAssignment.Task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationAssignment_001 {
	
	public static WebDriver Driver;
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\Java1\\Drivers\\chromedriver.exe");
		Driver = new ChromeDriver();
		AutomationAssignment_001 BrowserAct = new AutomationAssignment_001();
		
		BrowserAct.OpenAmazon();
		Thread.sleep(1500);
		BrowserAct.SelectCategory();
		Thread.sleep(1500);
		BrowserAct.ListOfItems();
		Thread.sleep(1500);
		BrowserAct.QuitAmazon();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql:@localhost:8088:xe","root","password");
			Statement State = con.createStatement();
			ResultSet Rs = State.executeQuery("Select * from product");
			while(Rs.next())
				System.out.println(Rs.getString(1) + " " + Rs.getString(2) + " " + Rs.getInt(3));
		}
		catch (Exception e){
			System.out.println(e);
		}
		
	}
	
	public void OpenAmazon() {
		Driver.manage().window().maximize();
		Driver.get("https://www.amazon.com");
	}
	
	public void SelectCategory() throws InterruptedException {
		Driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Fashion Champion Blank Apparel");
		Thread.sleep(1000);
		Driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		Thread.sleep(1000);
		Driver.findElement(By.xpath("//input[@id='high-price']")).sendKeys("10");
		Thread.sleep(1000);
		Driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
		Thread.sleep(1000);
		Driver.findElement(By.xpath("//a[@id='p_n_feature_eighteen_browse-bin/14630392011']")).click();
		System.out.println(Driver.findElement(By.xpath("//a[@id='p_n_feature_eighteen_browse-bin/14630392011']")).getText());
		System.out.println("----------------------------------------------------------------------------------------");
		Thread.sleep(1000);
	}
	
	public void ListOfItems() {
		WebElement ListOfItems = Driver.findElement(By.xpath("//div[@class='s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16']"));
		List<WebElement> rows = ListOfItems.findElements(By.tagName("h2"));
		for (WebElement rowEle : rows) {
			System.out.println(rowEle.getText());	
			System.out.println("----------------------------------------------------------------------------------------");
		}
	}
	
	public void QuitAmazon() {
		Driver.quit();
	}
}
