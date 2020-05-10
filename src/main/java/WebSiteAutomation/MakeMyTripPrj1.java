package WebSiteAutomation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MakeMyTripPrj1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//	1) Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
//	2) Click Hotels
		driver.findElementByXPath("(//span[@class='chNavText darkGreyText'])[2]").click();
		Thread.sleep(2000);
//	3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//input[@class='hsw_inputField font30 lineHeight36 latoBlack']")
		.sendKeys("Goa",Keys.TAB);
		
	/*	driver.findElementByXPath("//input[contains(@class,'react-autosuggest__input react-autosuggest__input--open')]")
		.sendKeys("Goa",Keys.TAB); */
		
//	4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5		
		//Check-in
		driver.findElementByXPath("(//div[contains(@class,'DayPicker-Day')])[55]").click();
		//Check out
		driver.findElementByXPath("(//div[contains(@class,'DayPicker-Day')])[60]").click();
		Thread.sleep(2000);  
		
//	5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.		
		driver.findElementByXPath("//input[@id='guest']").click();
		driver.findElementByXPath("(//li[@class='selected'])[1]").click();
		//driver.findElementByXPath("(//li[@class='selected'])[2]").click();
		driver.findElementByXPath("(//li[text()='1'])[2]").click();
		
		WebElement child = driver.findElementByClassName("ageSelectBox");
		Select age = new Select(child);
		age.selectByVisibleText("12");	
		
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click(); 

//	6) Click Search button
		driver.findElementByXPath("//button[@id='hsw_search_button']").click();
		Thread.sleep(2000);
		
//	7) Select locality as Baga
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
		
//	8) Select 5 start in Star Category under Select Filters
		//driver.findElementByXPath("//label[text()='5 Star']").click();
		driver.findElementByXPath("//label[text()='5 Star']").click();
		
//	9) Click on the first resulting hotel and go to the new window
		driver.findElementById("hlistpg_hotel_name").click();
		Set<String> win1 = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(win1);
		driver.switchTo().window(listOfWindows.get(1));
			
//	10) Print the Hotel Name
		String hotelName = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("The hotel name : " +hotelName);
		
//	11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		driver.findElementByXPath("//span[text()=\"SELECT\"]").click();
		driver.findElementByXPath("//span[@class=\"close\"]").click();

//	12) Click on BOOK THIS NOW
		driver.findElementByLinkText("BOOK THIS NOW").click();

//	13) Print the Total Payable amount
		String totalAmount = driver.findElementById("revpg_total_payable_amt").getText();
		String trimTotalAmount = totalAmount.replaceAll("[^0-9]", "");
		System.out.println(trimTotalAmount);
		
//	14) Close the browser
		driver.quit();
		
		
		
		
		
		
		

	}
}
