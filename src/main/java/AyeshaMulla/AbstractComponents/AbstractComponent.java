package AyeshaMulla.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AyeshaMulla.pageobjects.CartPage;
import AyeshaMulla.pageobjects.OrderPage;

public class AbstractComponent 
{
	WebDriver driver;
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cart; 
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(9));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(By findBy)
	{
		WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(9));
		w.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void windowScroll()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,500)");
	}
	
	public CartPage goToCart()
	{
		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}
	

}
