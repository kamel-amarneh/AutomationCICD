package kamelamarneh.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kamelamarneh.AbstractComponents.AbstractComponents;

public class MyCart extends AbstractComponents {

	WebDriver driver;
	@FindBy(css = ".cartSection h3")
	private List<WebElement> myCart;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement check;

	public MyCart(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean getCartList(String productName) {
		Boolean match = myCart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return match;
	}

	public CheckoutPage goToCheckout() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", check);
		js.executeScript("arguments[0].click();", check);
//		check.click();
		return new CheckoutPage(driver);
	}

}
