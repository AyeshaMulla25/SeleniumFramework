package AyeshaMulla.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AyeshaMulla.pageobjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AyeshaMulla\\resources\\GlobalData.properties");
		p.load(fis);
		String browserName = p.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ayesha.mulla_coditas\\Desktop\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		    driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		//WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException	
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to hashmap - jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
    }
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
    {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File (System.getProperty("user.dir")+"//reports"+testCaseName+".png");
    	FileUtils.copyFile(source,file);
    	return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
    }
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
	    lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
