package AyeshaMulla.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyeshaMulla.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver,this);
	
	}
	
	
	@FindBy(css="div[class='cartSection'] h3")
	private List<WebElement>cartItems ;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton ;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean b = cartItems.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return b;
	}
	
	public CheckOutPage goToCheckOut()
	{
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
	
	
	
	
	

}
