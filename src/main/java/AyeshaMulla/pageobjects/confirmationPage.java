package AyeshaMulla.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyeshaMulla.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent
{
WebDriver driver;
	
	public confirmationPage(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver,this);
	
	}
	
	@FindBy(css = ".hero-primary")
	WebElement text;
	
	By confirmMessage = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage()
	{
		waitForElementToAppear(confirmMessage);
		return text.getText();
	}

}
