package AssurantExampleHelper.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class OrderScreen extends AbstractComponents{

WebDriver driver;
	
	public OrderScreen(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//tr/td[2]")
	List<WebElement> orderlist;
	
	public boolean OrderVerify(String product_name)
	{
		Boolean match = orderlist.stream().anyMatch(order_product->order_product.getText().equalsIgnoreCase(product_name));;
		return match;
	}
}
