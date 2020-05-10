package WebSiteAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CRMCloudPrj1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "True");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//	1) Go to https://demo.1crmcloud.com/
		driver.get("https://demo.1crmcloud.com/login.php?login_module=Home&login_action=index");
//	2) Give username as admin and password as admin		
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");
//	3) Choose theme as Claro Theme
		WebElement eleTheme = driver.findElementById("login_theme");
		Select selClaro = new Select(eleTheme);
		selClaro.selectByVisibleText("Claro Theme");
		
		driver.findElementByXPath("//span[@class='uii uii-arrow-right']").click();
//	4) Click on Sales and Marketing 
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[@class='menu-label'])[2]").click();
		Thread.sleep(2000);
		
//	5) Click Create contact
		driver.findElementByXPath("(//div[@class='option-cell input-label '])[1]").click();
		Thread.sleep(2000);
		
//	6) Select Title and type First name, Last Name, Email and Phone Numbers
		//driver.findElementById("DetailFormsalutation-input").click();
		driver.findElementByXPath("(//div[@title='(none)'])[1]").click();
		driver.findElementByXPath("//div[text()='Mrs.']").click();

		driver.findElementByXPath("//input[@name='first_name']").sendKeys("Nourin1");
		driver.findElementByXPath("//input[@name='last_name']").sendKeys("Shaik");
		
		driver.findElementById("DetailFormemail1-input").sendKeys("shaiknourin4@gmail.com");
		driver.findElementByXPath("//input[@name='phone_mobile']").sendKeys("1234567890");
		
//	7) Select Lead Source as "Public Relations"
	/*	WebElement eleLead = driver.findElementByXPath("(//div[@class='input-field input-field-group rbullet'])[2]");
		Select selLeadSource = new Select(eleLead);
		selLeadSource.selectByVisibleText("Public Relations"); */
		
		driver.findElementByXPath("//p[text()=\"Lead Source\"]/following-sibling::div/div/div").click();
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		
		
//	8) Select Business Roles as "Sales"
		/* eleRole = driver.findElementByXPath("(//div[@class='input-field input-field-group rbullet'])[3]");
		Select selRoles = new Select(eleRole);
		selRoles.selectByVisibleText("Sales"); */
		
		driver.findElementByXPath("//input[@name='business_role']/following-sibling::div").click();
		driver.findElementByXPath("//div[text()='Sales']").click();
		
//	9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementByXPath("//textarea[@name='primary_address_street']").sendKeys("Marutham Prestige");
		driver.findElementByXPath("//input[@name='primary_address_city']").sendKeys("Chennai");
		driver.findElementByXPath("//input[@name='primary_address_state']").sendKeys("Tamilnadu");
		driver.findElementByXPath("//input[@name='primary_address_country']").sendKeys("India");
		driver.findElementByXPath("//input[@name='primary_address_postalcode']").sendKeys("600045");
		driver.findElementByXPath("//span[@id='DetailForm_save2-label']").click();
		
//	10) Mouse over on Today's Activities and click Meetings
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//div[(contains(text(),'Today'))]")).build().perform(); 
		//builder.moveToElement(driver.findElementByXPath("//div[@class=\"menu-label\"]")).perform();
		Thread.sleep(2000);
		//driver.findElementByXPath("//div[(contains(text(),'Meetings'))]").click();
		driver.findElementByXPath("//div[@id='menu-source-1-popup']/div/div[4]/div[2]").click();
		Thread.sleep(2000);
//	11) Click Create 
		driver.findElementByXPath("(//span[text()='Create'])[1]").click(); 
		Thread.sleep(2000);
		
//	12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		Thread.sleep(2000);
		driver.findElementById("DetailFormstatus-input").click(); 
		driver.findElementByXPath("(//div[text()='Planned'])[2]").click(); 
		
//	13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
		driver.findElementById("DetailFormdate_start-input").click(); 
		driver.findElementByXPath("//div[@class='grid-cell number-cell text-right day inside current selected quiet responsive']/following::div[1]").click(); 
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']").clear();
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input[@class='input-text']")
		.sendKeys("3:00pm", Keys.ENTER); 
		
		driver.findElementById("DetailFormduration-time").clear(); 
		driver.findElementById("DetailFormduration-time").sendKeys("1hr", Keys.TAB);
		
//	14) Click Add paricipants, add your created Contact name and click Save
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()=' Add Participants']").click();
		String srchtxt = "Sony";
		driver.findElementByXPath("//div[@id='app-search-text']//input").sendKeys(srchtxt);
		driver.findElementByXPath("(//span[text()='Search Results (Invitees)']/ancestor::div[@class='card-header panel-subheader'])/following-sibling::div//div[contains(text(),'" + srchtxt + "')]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		Thread.sleep(2000);
		
//	15) Go to Sales and Marketing-->Contacts
		WebElement ele2 = driver.findElementByXPath("//div[text()='Sales & Marketing']");
		Actions action = new Actions(driver);
		action.moveToElement(ele2).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='Contacts']").click();
		Thread.sleep(1000);
		
//	16) search the lead Name and click the name from the result
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@id='filter_text']").sendKeys(srchtxt);
		driver.findElementByXPath("//input[@id='filter_text']").sendKeys(Keys.ENTER);
		driver.findElementByXPath("//td[@class='listViewTd']//a[contains(text(),'"+ srchtxt + "')]").click();
		Thread.sleep(2000);
		
//	17) Check whether the Meeting is assigned to the contact under Activities Section.
		WebElement meeting = driver.findElementByXPath("(//span[@id='subpanel-activities']/ancestor::div[@id='DetailForm-subpanels']//a[contains(text(),'Project Status')])[1]"); 
		if (meeting.isDisplayed()) {
			System.out.println("Meeting is assigned"); 
		} else { 
			System.out.println("Meeting is not assigned");
		}
		
		
	}
}
