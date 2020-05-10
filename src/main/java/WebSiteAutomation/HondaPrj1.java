package WebSiteAutomation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HondaPrj1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//	1) Go to https://www.honda2wheelersindia.com/
		driver.get("https://www.honda2wheelersindia.com/");
		driver.findElementByClassName("close").click();
		Thread.sleep(2000);
		
//	2) Click on scooters and click dio
		driver.findElementById("link_Scooter").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class='owl-item'])[4]").click();

//	3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(2000);
		WebElement eleEngine = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions actEngine = new Actions(driver);
		actEngine.moveToElement(eleEngine).pause(2000).perform();
				
//	4) Get Displacement value
		String strDisplacementVal = driver.findElementByXPath("//span[text()='Displacement']/following::span").getText();
		float dio_displacement = Float.parseFloat(strDisplacementVal.replaceAll("c",""));
		System.out.println("Dio Displacement value - "+strDisplacementVal);
		System.out.println("Dio displacement value is - " +dio_displacement);
		
//	5) Go to Scooters and click Activa 125
		driver.findElementById("link_Scooter").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class='owl-item'])[6]").click();
		Thread.sleep(2000);

//	6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//*[@id=\"accordion-1\"]/ul/li[4]/a").click();
		Thread.sleep(2000);
		WebElement eleEngine125 = driver.findElementByXPath("(//li[@class='specificationsLi']//a)[4]");
		Actions actEngine125 = new Actions(driver);
		actEngine.moveToElement(eleEngine125).pause(2000).perform();
		
//	7) Get Displacement value
		String strDispVal125 = driver.findElementByXPath("//*[@id=\"main-body\"]/div/section[1]/div[2]/div[2]/div/div[2]/div[2]/ul/li[3]/span[2]").getText();
		float activa_displacement = Float.parseFloat(strDispVal125.replaceAll("c",""));
		System.out.println("Active125 Displacement Value is - " + strDispVal125);
		System.out.println("Activa displacement value is - " +activa_displacement);
		
// 	8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(dio_displacement<activa_displacement) {
			System.out.println("Activa is having better dipacement");
		}
		else {
			System.out.println("Dio is having better displacement");
		}
			
//	9) Click FAQ from Menu 
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
		
//	10) Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
		
//	11) Click  Vehicle Price 
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
			
//	12) Make sure Activa 125 BS-VI selected and click submit
		String productName = driver.findElementByXPath("//select[@id='ModelID6']").getText();
		System.out.println("Selected product : " +productName);
		
		driver.findElementByXPath("//button[@onclick='validateformPriceMaster(6)']").click();
		
//	13) click the price link
		driver.findElementByXPath("//a[contains(text(),'Click here to know the price ')]").click();
			
//	14) Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> window1 = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(window1);
		driver.switchTo().window(listOfWindows.get(1));
		
		WebElement eleState = driver.findElementByXPath("//select[@id='StateID']");
		Select selTN = new Select(eleState);
		selTN.selectByVisibleText("Tamil Nadu");
		
		WebElement eleCity = driver.findElementByXPath("//select[@id='CityID']");
		Select selChennai = new Select(eleCity);
		selChennai.selectByVisibleText("Chennai");
				
//	15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		Thread.sleep(2000);
		
//	16) Print all the 3 models and their prices
		
		List<WebElement> row = driver.findElementsByXPath("//table[@id='gvshow']//tr");
		for (int i = 2; i < row.size(); i++) {
			List<WebElement> col = row.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < col.size(); j++) {
				System.out.println(col.get(j).getText());
			}
		}
		Thread.sleep(2000);
				
//	17)	Close Browser
		driver.quit();
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
