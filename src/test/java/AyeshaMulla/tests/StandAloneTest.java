package AyeshaMulla.tests;



import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AyeshaMulla.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args)
	{
		String productName = "adidas original";
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\\\Users\\\\ayesha.mulla_coditas\\\\Desktop\\\\Selenium\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("anshula@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anshula@25");
		driver.findElement(By.id("login")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement itemName  = products.stream().filter(product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
	    itemName.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	    w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	    driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	    List<WebElement> cartItems = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	    
	    Boolean b = cartItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(b);
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,500)");
	    driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    driver.close();

	}

	

}
