package AyeshaMulla.tests;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AyeshaMulla.TestComponents.BaseTest;
import AyeshaMulla.pageobjects.CartPage;
import AyeshaMulla.pageobjects.CheckOutPage;
import AyeshaMulla.pageobjects.OrderPage;
import AyeshaMulla.pageobjects.ProductCatalog;
import AyeshaMulla.pageobjects.confirmationPage;

public class SubmitOrderTest extends BaseTest
{
	String productName = "ADIDAS ORIGINAL";

	    @Test (dataProvider = "getData", groups= {"purchase"})
	    public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	    {
		ProductCatalog pc = lp.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));
		CartPage cp = pc.goToCart();
		Boolean b = cp.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(b);
	    CheckOutPage cop = cp.goToCheckOut();
	    cop.selectCountry("india");
	    confirmationPage cpage = cop.submitOrder();
	    String text = cpage.getConfirmationMessage();
	    Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    }
	    
	    @Test(dependsOnMethods= {"SubmitOrder"})
	    public void OrderHistoryTest()
	    {
	    	ProductCatalog pc = lp.loginApplication("anshula@gmail.com", "Anshula@25");
	    	OrderPage op = pc.goToOrdersPage();
	    	Assert.assertTrue(op.VerifyOrderDisplay(productName));
	    }
	    
	 //   @DataProvider
	 //   public Object[][] getData()
	 //   {
	 //   	return new Object[][] {{"anshula@gmail.com","Anshula@25","ADIDAS ORIGINAL"},{"anshul@gmail.com","Anshula@25","ZARA COAT 3"}};
	 //  }
	    
	    @DataProvider
	    public Object[][] getData() throws IOException
	    {
	    //	HashMap<String,String> map = new HashMap<String,String>();
	    //	map.put("email","anshula@gmail.com");
	    //	map.put("password","Anshula@25");
	    //	map.put("product","ADIDAS ORIGINAL");
	    	
	    //	HashMap<String,String> map1 = new HashMap<String,String>();
	    //	map1.put("email","anmolgupta@gmail.com");
	    //	map1.put("password","Anmol@96");
	    //	map1.put("product","ZARA COAT 3");
	    	
	    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AyeshaMulla\\data\\PurchaseOrder.json");
	    	return new Object[][] {{data.get(0)},{data.get(1)}};
	    	
	    }
	    
	    
	

}


