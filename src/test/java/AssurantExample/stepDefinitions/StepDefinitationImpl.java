package AssurantExample.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AssurantExample.TestComponents.BaseData;
import AssurantExampleHelper.pageobjects.CartScreen;
import AssurantExampleHelper.pageobjects.Checkoutpage;
import AssurantExampleHelper.pageobjects.FinalPage;
import AssurantExampleHelper.pageobjects.LoginPage;
import AssurantExampleHelper.pageobjects.Productcatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitationImpl extends BaseData{
	
	public LoginPage loginPage;
	public Productcatalogue Productcatalogue;
	public FinalPage finalPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		loginPage = lunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		Productcatalogue = loginPage.HelpLoginApplication(username,password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName)
	{
		List<WebElement> Products = Productcatalogue.GetProductList();
		Productcatalogue.AddtoCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_product_and_submit_the_order(String productName)
	{
		CartScreen cartScreen = Productcatalogue.CartPage();				
		Boolean find = cartScreen.ProductListVerify(productName);
		Assert.assertTrue(find);
		Checkoutpage checkoutpage = cartScreen.Checkout();
		checkoutpage.SelectCountry("india");
		finalPage = checkoutpage.Submit();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void Then_message_is_displayed_on_confirmationPage(String string)
	{
		String confirmationmessage = finalPage.GetConfirmationmessage();		
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed on Loginpage")
	public void Error_message_is_displayed_on_Loginpage(String string)
	{
		Assert.assertEquals(string, loginPage.Geterrormessage());
		driver.close();
	}
	
}
