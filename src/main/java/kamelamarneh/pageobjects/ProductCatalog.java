package kamelamarneh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kamelamarneh.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css = ".card-body button:last-of-type")
	WebElement addToCart;

	By productsBy = By.cssSelector(".mb-3");

	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByname(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByname(productName);
		
	    WebElement addToCartButton = prod.findElement(By.cssSelector(".card-body button:last-of-type"));

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
		js.executeScript("arguments[0].click();", addToCartButton);
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear();

	}

}
