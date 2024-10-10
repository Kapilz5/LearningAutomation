package AssurantExample.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AssurantExampleHelper.pageobjects.LoginPage;


public class BaseData {
	
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializerDriver() throws IOException
	{
		
		Properties prod = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AssurantExampleHelper\\Resource\\GlobalData.properties");
		//FileInputStream fis = new FileInputStream("C:\\Users\\kapil\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\AssurantExampleHelper\\Resource\\GlobalData.properties");
		prod.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prod.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions(); //to use chrome options
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options); 
			driver.manage().window().maximize(); //to use maximize mode in headless mode
			
			//user can use: driver.manager().window().setSize(new Dimension(1400,900)); as well to max size of browser
		}
		else if(browserName.equalsIgnoreCase("FireFox"))
		{
			//Firefox Code
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	
	
	//To Read Json file data
	public List<HashMap<String, String>> getjsondatatomap(String FilePath) throws IOException
	{
		String jsoncontent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	//to get the screenshot 
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	

	@BeforeMethod(alwaysRun = true)
	public LoginPage lunchApplication() throws IOException
	{
		driver = initializerDriver();
		loginPage = new LoginPage(driver);
		loginPage.GoTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void ClosePage()
	{
		driver.quit();
	}
}
