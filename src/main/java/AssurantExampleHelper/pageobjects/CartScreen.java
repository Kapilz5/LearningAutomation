package AssurantExampleHelper.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class CartScreen extends AbstractComponents{
	
	WebDriver driver;
	
	public CartScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'cartSection']/h3")
	List<WebElement> cart_products;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout_button;
	
	public boolean ProductListVerify(String product_name)
	{
		Boolean match = cart_products.stream().anyMatch(cart_product->cart_product.getText().equalsIgnoreCase(product_name));;
		return match;
	}

	public Checkoutpage Checkout()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(checkout_button).click().perform();
		//checkout_button.click();
		Checkoutpage checkoutpage = new Checkoutpage(driver);
		return checkoutpage;
	}
}
