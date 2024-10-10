package AssurantExampleHelper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class Checkoutpage extends AbstractComponents {
	
	WebDriver driver;

	public Checkoutpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	By result = By.cssSelector(".list-group:nth-of-type(1)");
	
	@FindBy(xpath = "(//section[contains(@class,'ta-results')]/button)[2]")
	WebElement selectCountry;
	
	@FindBy(xpath = "//a[contains(@class , 'action__submit')]")
	WebElement submit;

	public void SelectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		Waitforelementvisibility(result);
		selectCountry.click();
	}
	
	public FinalPage Submit()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(submit).click().perform();
		//submit.click();
		FinalPage finalpage = new FinalPage(driver);
		return finalpage;
	}
}