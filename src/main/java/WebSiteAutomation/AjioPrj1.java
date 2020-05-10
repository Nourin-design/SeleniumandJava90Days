package WebSiteAutomation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class AjioPrj1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "True");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//	1) Go to https://www.ajio.com/
		driver.get("https://www.ajio.com/shop/sale");
		
//	2) Enter Bags in the Search field and Select Bags in Women Handbags   
		//driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
		driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Bags");
		driver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();
		
//	3) Click on five grid and Select SORT BY as "What's New"   
		driver.findElementByXPath("//div[@class='five-grid']").click();
		//driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]").click();
		
		WebElement eleNew = driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]");
		Select selWhatsNew = new Select(eleNew);
		selWhatsNew.selectByVisibleText("What's New");
		Thread.sleep(2000);
		
//	4) Enter Price Range Min as 2000 and Max as 5000   	
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementByXPath("//input[@name='minPrice']").sendKeys("2000");
		driver.findElementByXPath("//input[@name='maxPrice']").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
	//	Thread.sleep(2000);
		
//	5) Click on the product "Puma Ferrari LS Shoulder Bag" 
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Thread.sleep(2000);
		try {
			driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		Set<String> window1 = driver.getWindowHandles();
		//System.out.println(window1.size());
		List<String> listOfWindows = new ArrayList<String>(window1);
		Thread.sleep(2000);
		driver.switchTo().window(listOfWindows.get(1));
				
//	6) Verify the Coupon code for the price above 2690 is applicable for your product,
//		if applicable the get the Coupon Code and Calculate the discount price for the coupon   
		String bagAmount = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		System.out.println("Cost of the Bag : " +bagAmount);
		int cost = Integer.parseInt(bagAmount.replaceAll("\\D", ""));
		System.out.println("Product amount :" +cost);
		
		if(cost>=2690) {
			String couponCode = driver.findElementByXPath("//div[@class= 'promo-title']").getText();
			System.out.println("Coupon is applicable & Code is : " +couponCode);
		}	
		else {
			System.out.println("Coupon code is not applicable");
		}
		
		String discount = driver.findElementByXPath("//span[@class='prod-discnt']").getText();
		System.out.println("Discount cost is :" +discount);
		
//	7) Check the availability of the product for pincode 560043, 
		//print the expected delivery date if it is available
		driver.findElementByXPath("//span[contains(text(),\"Enter pin-code\")]").click();
		driver.findElementByName("pincode").sendKeys("600045");
		driver.findElementByClassName("edd-pincode-modal-submit-btn").click();

//	8) Click on Other Informations under Product Details 
		//and Print the Customer Care address, phone and email
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		String addr = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		System.out.println("Address: " + addr);
		
//	9) Click on ADD TO BAG and then GO TO BAG   
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class=\"ic-cart \"]").click();
		
//	10) Check the Order Total before apply coupon   
		String orderBag = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		String orderBagStr1 = orderBag.replaceAll("[^0-9.]", "");
		String orderBagStr2 = orderBagStr1.substring(1);
		System.out.println("Order Value Before applying coupon: " + orderBagStr2);

//	11) Enter Coupon Code and Click Apply   
		driver.findElementById("couponCodeInput").sendKeys(discount);
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Apply']").click();
	
//	12) Verify the Coupon Savings amount(round off if it in decimal) 
		//under Order Summary and the matches the amount calculated in Product details
		String summaryCouponSavings = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
		System.out.println("Summary Coupon Savings Amount is :"+summaryCouponSavings);
		String num = summaryCouponSavings.replaceAll("Rs.","");
		double amount = Double.parseDouble(num);
		
		int couponAmount = (int) Math.round(amount);

	/*	if(couponAmount==discount)
			System.out.println("Order Summary matches ");
		else
			System.out.println("Order Summary does not match");
		*/
		
//	13) Click on Delete and Delete the item from Bag 
		driver.findElementByClassName("delete-btn").click();
		driver.findElementByXPath("//div[text()='DELETE']").click();
				
//	14) Close all the browsers
		driver.quit();		
		
		
		
	}
}
