package AyeshaMulla.tests;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AyeshaMulla.TestComponents.BaseTest;
import AyeshaMulla.TestComponents.Retry;
import AyeshaMulla.pageobjects.CartPage;
import AyeshaMulla.pageobjects.CheckOutPage;
import AyeshaMulla.pageobjects.OrderPage;
import AyeshaMulla.pageobjects.ProductCatalog;
import AyeshaMulla.pageobjects.confirmationPage;

public class ErrorValidationsTest extends BaseTest
{
	

	    @Test (groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	    public void LoginErrorValidations() throws IOException, InterruptedException
	    {
	    	String productName = "ADIDAS ORIGINAL";
	        lp.loginApplication("anshula@gmail.com","Anshul@25");
	        Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	    }
	    
	    public void ProductErrorValidation() throws IOException, InterruptedException
	    {
	    	String productName = "ADIDAS ORIGINAL";
		ProductCatalog pc = lp.loginApplication("anmolgupta@gmail.com","Anmol@96");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCart();
		Boolean b = cp.VerifyProductDisplay("ADIDAS ORIGINAL 1");
	    Assert.assertFalse(b);
	    
	    }
		
		
	    	
	    
	    
	

}


