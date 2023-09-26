package AyeshaMulla.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyeshaMulla.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent
{
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver,this);
	
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectOption;
	
	@FindBy(css =".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryName)
	{
		windowScroll();
	    country.sendKeys(countryName);
	    waitForElementToAppear(results);
	    selectOption.click();
	    
	    
	}
	
	public confirmationPage submitOrder()
	{
		submit.click();
		return new confirmationPage(driver);
	}
	
	

}
