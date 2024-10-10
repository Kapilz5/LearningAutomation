package AssurantExample.Test;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AssurantExample.TestComponents.BaseData;
import AssurantExampleHelper.pageobjects.CartScreen;
import AssurantExampleHelper.pageobjects.Productcatalogue;


public class ErrorMessageVerification extends BaseData {

	@Test (groups = {"errovalidation"})
	public void LoginErrorMessage() throws IOException, InterruptedException {
		
		String errorMessage = "Incorrect email or password.";
		loginPage.HelpLoginApplication("kapilmike@test.com", "Wrongpwd@");
		
		String returnedMessage = loginPage.Geterrormessage();
		
		Assert.assertEquals(errorMessage, returnedMessage);
		
}
	
	@Test
	public void ProductErrorMessage() throws IOException, InterruptedException {
		
		String product_name = "ZARA COAT 3";
		Productcatalogue Productcatalogue = loginPage.HelpLoginApplication("kkaapp@test.com", "kkaapp@test.comA@1");
		Productcatalogue.GetProductList();
		Productcatalogue.AddtoCart(product_name);
		CartScreen cartScreen = Productcatalogue.CartPage();
		Boolean find = cartScreen.ProductListVerify(product_name);
		Assert.assertTrue(find);
	}

}
