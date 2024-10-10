package AssurantExampleHelper.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class Productcatalogue extends AbstractComponents{

	WebDriver driver;
	
	public Productcatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productId = By.xpath("//div[contains(@class,'mb-3')]");
	By cart = By.xpath("//button[2]");
	By toastmessage = By.cssSelector("#toast-container");

	public List<WebElement> GetProductList()
	{
		Waitforelementvisibility(productId);
		return products;
	}
	
	public WebElement ProductName(String product_name)
	{
		WebElement prod = products.stream().filter(product->product.findElement(By.xpath("//div[@class='card-body']/h5/b"))
				  .getText().equalsIgnoreCase(product_name)).findFirst().orElse(null);
		return prod;
	}
	
	public void AddtoCart(String product_name)
	{
		WebElement prod = ProductName(product_name);
		prod.findElement(cart).click();
		Waitforelementvisibility(toastmessage);
		Waitforelementinvisibility(spinner);
	}
}
