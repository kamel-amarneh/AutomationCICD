package kamelamarneh.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kamelamarneh.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement placeOrder;

	@FindBy(css = ".ta-results")
	List<WebElement> options;

	By countryData = By.cssSelector(".ta-results");

	public CheckoutPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void Information(String countryName, String suggestion) {
	    country.sendKeys(suggestion);
	    waitForElementToAppear(countryData);

	    // انتظر لاختفاء أي overlay إذا في
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

	    List<WebElement> options = driver.findElements(By.cssSelector(".ta-results button")); // عدّل السلكتور حسب HTML

	    for (WebElement option : options) {
	        if (option.getText().contains(countryName)) {
	            new WebDriverWait(driver, Duration.ofSeconds(10))
	                .until(ExpectedConditions.elementToBeClickable(option));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
	            try {
	                option.click();
	            } catch (ElementClickInterceptedException e) {
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	            }
	            break;
	        }
	    }
	}
	
	public ConfirmationPage PlaceOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		js.executeScript("arguments[0].click();", placeOrder);
		return new ConfirmationPage(driver);
	}

}
