package AssurantExampleHelper.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AssurantExampleHelper.Abstractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents{

		WebDriver driver;
		
		public LoginPage(WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		//WebElement useremail = driver.findElement(By.id("userEmail")); instead of this, you can use below pageFactory method
		
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement userPassword;
		
		@FindBy(id="login")
		WebElement login;
		
		@FindBy(css = "[class*='flyInOut']")
		WebElement errormessage;
		
		public Productcatalogue HelpLoginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			login.click();
			Productcatalogue Productcatalogue = new Productcatalogue(driver);
			return Productcatalogue;
		}
		
		public String Geterrormessage()
		{
			WaitforWebelementvisibility(errormessage);
			return errormessage.getText();
		}
		
		public void GoTo()
		{
			driver.get("https://rahulshettyacademy.com/client");

		}
}
