package WebSiteAutomation;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PepperFryPrj1 {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "True");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//	1) Go to https://www.pepperfry.com/ 
		driver.get("https://www.pepperfry.com/");
		
//	2) Mouseover on Furniture and click Office Chairs under Chairs 
		WebElement furtniture = driver.findElementByXPath("(//a[@class='level-top'])[1]");
		Actions act1 = new Actions(driver);
		act1.moveToElement(furtniture).pause(2000).perform();
		act1.click(driver.findElementByXPath("//*[@id=\"meta-furniture\"]/div/div[3]/div[2]/div[12]/a"))
		.perform(); 
		
//	3) click Executive Chairs 
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();

//	4) Change the minimum Height as 50 in under Dimensions 
		WebElement eleHeight = driver.findElementByXPath("//input[@class='clipFilterDimensionHeightValue']");
		eleHeight.clear();
		eleHeight.sendKeys("50",Keys.TAB);
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait (driver, 30);
		
//	5) Add "Poise Executive Chair in Black Colour" chair to Wishlist 
		//driver.findElementByXPath("(//a[@id='clip_wishlist_'])[2]").click();
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		//driver.findElementByXPath("//a[@class='clip-heart-icn pf-right active-wishlist']").click();
		
//	6) Mouseover on Homeware and Click Pressure Cookers under Cookware 
		WebElement homeWare = driver.findElementByXPath("(//a[@class='level-top'])[8]");
		Actions act2 = new Actions(driver);
		act2.moveToElement(homeWare).pause(2000).perform();
		act2.click(driver.findElementByXPath("//a[text()='Pressure Cookers']")).perform();
		
//	7) Select Prestige as Brand 
		driver.findElementByXPath("//label[text()='Prestige']").click();
		Thread.sleep(2000);
		
//	8) Select Capacity as 1-3 Ltr 
		driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		//label[@for='capacity_db1_Ltr_-_3_Ltr']
		Thread.sleep(2000);
//	9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist 
		//driver.findElementByXPath("(//a[@class='clip-heart-icn pf-right'])[3]").click();
		driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();
		
		Thread.sleep(2000);
//	10) Verify the number of items in Wishlist 
		String wishCount = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		System.out.println("Wishlist items : " +wishCount);
		
//	11) Navigate to Wishlist 
		driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();
		
// 	12) Move Pressure Cooker only to Cart from Wishlist 
		driver.findElementByXPath("(//a[@class='addtocart_icon'])[2]").click();
		
//	13) Check for the availability for Pincode 600128 
		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600045");
		
		//handle sign in popup
		driver.findElement(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).click();
		Thread.sleep(2000);
		//driver.navigate().refresh();
//	14) Click Proceed to Pay Securely;
		driver.findElementByXPath("//a[@class='proceed_cta']").click();
		//driver.findElementByLinkText("Proceed to pay securely").click();
		Thread.sleep(2000);
//	15 Click Proceed to Pay
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		Thread.sleep(2000);
//	16) Capture the screenshot of the item under Order Item 
		driver.findElementByXPath("//span[text()='ORDER SUMMARY']").click();
		Thread.sleep(2000);
		
		WebElement itemImg = driver.findElementByXPath("//img[@src='https://ii1.pepperfry.com/media/catalog/product/p/r/90x99/prestige-nakshatra-cute-metallic-red-aluminium-cooker--2000-ml-prestige-nakshatra-cute-metallic-red--eshzll.jpg']");
		File src1 = itemImg.getScreenshotAs(OutputType.FILE);
		File dest1 = new File("./snaps/OrderImage.png");
		FileUtils.copyFile(src1, dest1);
		
//	17) Close the browser
		driver.close();
		
		
		
	}

}
