package AssurantExample.Test;


import java.io.IOException;
import AssurantExample.TestComponents.Retry;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AssurantExample.TestComponents.BaseData;
import AssurantExampleHelper.pageobjects.CartScreen;
import AssurantExampleHelper.pageobjects.Checkoutpage;
import AssurantExampleHelper.pageobjects.FinalPage;
import AssurantExampleHelper.pageobjects.OrderScreen;
import AssurantExampleHelper.pageobjects.Productcatalogue;


public class Standalonetest extends BaseData{
	
	//public String product_name = "ZARA COAT 3";


	@Test (dataProvider="dataprovider",groups = {"purchaseorder"}, retryAnalyzer=Retry.class)
	public void SubmitOrder(HashMap<String,String> input) throws IOException {
		
		String country = "india";		
		Productcatalogue Productcatalogue = loginPage.HelpLoginApplication(input.get("email"), input.get("pwd"));
		
		List<WebElement> Products = Productcatalogue.GetProductList();
				
		Productcatalogue.AddtoCart(input.get("product_name"));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		CartScreen cartScreen = Productcatalogue.CartPage();
		
		//driver.findElement(By.xpath("//button[@routerlink = '/dashboard/cart']")).click();
				
		Boolean find = cartScreen.ProductListVerify(input.get("product_name"));
		
		//List<WebElement> cart_products = driver.findElements(By.xpath("//div[@class = 'cartSection']/h3"));
		
		//Boolean find = cart_products.stream().anyMatch(cart_product->cart_product.getText().equalsIgnoreCase(product_name));
		Assert.assertTrue(find);
		
		Checkoutpage checkoutpage = cartScreen.Checkout();
	
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		
		checkoutpage.SelectCountry(country);
		
		/*Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group:nth-of-type(1)")));
		
		driver.findElement(By.xpath("(//section[contains(@class,'ta-results')]/button)[2]")).click();*/
		
		FinalPage finalPage = checkoutpage.Submit();
		
		//driver.findElement(By.xpath("//a[contains(@class , 'action__submit')]")).click();
		
		
		String confirmationmessage = finalPage.GetConfirmationmessage();
		
		//String order = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		String product_name = "ZARA COAT 3";
		Productcatalogue Productcatalogue = loginPage.HelpLoginApplication("testkapil1@test.com", "testkapil1A@");
		OrderScreen orderPage = Productcatalogue.OrderPage();
		Assert.assertTrue(orderPage.OrderVerify(product_name));
	}
	
	@DataProvider
	public Object[][] dataprovider() throws IOException
	{
		//to call the getjsondatatomap method from BaseData.java file to read the json file located in AssurantExample.Data folder with name as Purchaseorder.json
		List<HashMap<String, String>> data = getjsondatatomap(System.getProperty("user.dir")+"\\src\\test\\java\\AssurantExample\\Data\\Purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "testkapil1@test.com");
//		map.put("pwd", "testkapil1A@");
//		map.put("product_name", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "kkaapp@test.com");
//		map1.put("pwd", "kkaapp@test.comA@1");
//		map1.put("product_name", "IPHONE 13 PRO");
//	    return new Object[][] {{map},{map1}};

		
		//return new Object[][] {{"testkapil1@test.com", "testkapil1A@","IPHONE 13 PRO"},{"kkaapp@test.com", "kkaapp@test.comA@1","ZARA COAT 3"}};
	}

}
