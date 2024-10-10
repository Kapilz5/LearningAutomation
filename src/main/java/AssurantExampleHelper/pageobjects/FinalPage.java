package AssurantExampleHelper.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class FinalPage extends AbstractComponents{
	
	WebDriver driver;

	public FinalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement message;
	
	public String GetConfirmationmessage()
	{
		String confirmationmessage = message.getText();
		return confirmationmessage;
	}
}
