package kamelamarneh.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kamelamarneh.TestComponents.BaseTest;
import kamelamarneh.pageobjects.CheckoutPage;
import kamelamarneh.pageobjects.ConfirmationPage;
import kamelamarneh.pageobjects.LandingPage;
import kamelamarneh.pageobjects.MyCart;
import kamelamarneh.pageobjects.ProductCatalog;

public class StepDefinitionimpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecomarce Page")
	public void I_landed_on_Ecomarce_Page() throws IOException {

		landingPage = launchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) throws InterruptedException {
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	
	@When("^I add product (.+) to Cart$")
	public void When_I_add_product_to_Cart(String productName) throws InterruptedException {
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		MyCart myCart = productCatalog.cart();
		Boolean match = myCart.getCartList(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = myCart.goToCheckout();
		checkoutPage.Information("Jordan", "jor");
		confirmationPage = checkoutPage.PlaceOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String message = confirmationPage.confirmation();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.quit();
	}
	@Then("{string} message is displayed on LoginPage")
	public void message_is_displayed_on_LoginPage(String string) throws InterruptedException {
		Assert.assertEquals(string, landingPage.getErrorrMessage());
		driver.quit();
	}
	
}
