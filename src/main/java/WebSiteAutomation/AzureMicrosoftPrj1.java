package WebSiteAutomation;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AzureMicrosoftPrj1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_32.exe");
		System.setProperty("webdriver.chrome.silentOutput", "True");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(option);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//	1) Go to https://azure.microsoft.com/en-in/ 
		driver.get("https://azure.microsoft.com/en-in/");

//	2) Click on Pricing 
		driver.findElementById("navigation-pricing").click();

//	3) Click on Pricing Calculator 
		driver.findElementByLinkText("Pricing calculator").click();

//	4) Click on Containers 
		driver.findElementByXPath("//button[text()='Containers']").click();

//	5) Select Container Instances 
		driver.findElementByXPath("(//span[text()='Easily run containers on Azure without managing servers'])[3]").click();
	
//	6) Click on Container Instance Added View 
		driver.findElementByLinkText("View").click();
		Thread.sleep(2000);
//	7) Select Region as "South India" 
		WebElement eleRegion = driver.findElementByXPath("(//select[@name='region'])[1]");
		Select selregion = new Select(eleRegion);
		selregion.selectByVisibleText("South India");
		Thread.sleep(2000);
//	8) Set the Duration as 180000 seconds 
		driver.findElementByXPath("(//input[@aria-label='Seconds'])[1]").sendKeys(Keys.chord(Keys.CONTROL,"a"),"180000");
		//Keys.ARROW_LEFT or   clear and keys.backspace
		//Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END)
		Thread.sleep(2000);
		
//	9) Select the Memory as 4GB 
		WebElement eleMemory = driver.findElementByXPath("(//select[@aria-label='Memory'])[1]");
		Select selMemory = new Select(eleMemory);
		selMemory.selectByVisibleText("4 GB");
		Thread.sleep(2000);
		
//	10) Enable SHOW DEV/TEST PRICING 
		//driver.findElementByXPath("(//label[@for='devtest-toggler']//div)[1]").click();
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		
//	11) Select Indian Rupee  as currency 
		WebElement eleCurrency = driver.findElementByXPath("(//select[@aria-label='Currency'])[1]");
		Select selCurrency = new Select(eleCurrency);
		selCurrency.selectByValue("INR");
		
//	12) Print the Estimated monthly price 
		String estimatedCost = driver.findElementByXPath("(//div[@class='column large-3 text-right total']//span[@class='numeric']/span)[2]").getText();
		//numeric
		System.out.println("Estimated Cost : " +estimatedCost);
		
//	13) Click on Export to download the estimate as excel 
		driver.findElementByXPath("(//button[contains(@class,'calculator-button button-transparent')])[4]").click();
		File estimatedFile = new File("C:\\Users\\User\\Downloads\\ExportedEstimate.xlsx");
		
        if (estimatedFile.exists()) 
            System.out.println("Estimated file is exists"); 
        else
            System.out.println("Estimated file does not exists"); 
		
//	14) Navigate to Example Scenarios and Select CI/CD for Containers 
		WebElement eleExScenario = driver.findElementByLinkText("Example Scenarios");
		Actions actExScenario = new Actions(driver);
		actExScenario.moveToElement(eleExScenario).click().perform();
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		
//	15) Click Add to Estimate 
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		Thread.sleep(3000);
		
//	16) Change the Currency as Indian Rupee
		WebElement eleCurrency1 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selCurrency1 = new Select(eleCurrency1);
		selCurrency1.selectByValue("INR");
		Thread.sleep(2000);
		
//	17) Enable SHOW DEV/TEST PRICING 
		driver.findElementById("devtest-toggler").click(); 		
		
//	18) Export the Estimate 
		driver.findElementByXPath("(//button[contains(@class,'calculator-button button-transparent')])[4]").click();
		
		
		
		
		
		
		
	}

}
