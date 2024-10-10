package AssurantExampleHelper.Abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AssurantExampleHelper.pageobjects.CartScreen;
import AssurantExampleHelper.pageobjects.OrderScreen;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	public void  Waitforelementvisibility(By findBy)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void  WaitforWebelementvisibility(WebElement findBy)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void  Waitforelementinvisibility(WebElement spinner)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}

	@FindBy(xpath = "//button[@routerlink = '/dashboard/cart']")
	WebElement cartpage;
	
	@FindBy(xpath = "//button[@routerlink = '/dashboard/myorders']")
	WebElement orders;
	public CartScreen CartPage()
	{
		cartpage.click();
		CartScreen cartScreen = new CartScreen(driver);
		return cartScreen;
	}
	
	public OrderScreen OrderPage()
	{
		orders.click();
		OrderScreen orderScreen = new OrderScreen(driver);
		return orderScreen;
	}
}
