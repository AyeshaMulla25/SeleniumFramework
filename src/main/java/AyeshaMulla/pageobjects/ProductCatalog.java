package AyeshaMulla.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AyeshaMulla.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent
{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver,this);
	
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By loader = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList()
	{
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
	
	WebElement itemName  = products.stream().filter(product -> product.findElement(By.cssSelector(".mb-3 b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
    return itemName;
	
	}
	public void addProductToCart(String productName)
	{
	WebElement itemName = getProductByName(productName);
	itemName.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);
	waitForElementToDisappear(loader);
	
	
	}
}
