package kamelamarneh.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import kamelamarneh.TestComponents.BaseTest;
import kamelamarneh.pageobjects.CheckoutPage;
import kamelamarneh.pageobjects.ConfirmationPage;
import kamelamarneh.pageobjects.MyCart;
import kamelamarneh.pageobjects.OrderPage;
import kamelamarneh.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalog.addProductToCart(input.get("productName"));
		MyCart myCart = productCatalog.cart();

		Boolean match = myCart.getCartList(input.get("productName"));

		Assert.assertTrue(match);

		CheckoutPage checkoutPage = myCart.goToCheckout();
		checkoutPage.Information(input.get("countryName"), input.get("suggestion"));
		ConfirmationPage confirmationPage = checkoutPage.PlaceOrder();

		String message = confirmationPage.confirmation();

		System.out.println(message);
		
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication("noritof738@coursora.com", "Hello1234*");

		OrderPage orderPage = productCatalog.gpToOrdersPage();

		Assert.assertTrue(orderPage.VerifyOrderDisplay("ZARA COAT 3"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/kamelamarneh/data/Purchase.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}

//HashMap<String, String> map=new HashMap<String, String>();
//map.put("email", "noritof738@coursora.com");
//map.put("password", "Hello1234*");
//map.put("productName", "ZARA COAT 3");
//map.put("countryName", "Jordan");
//map.put("suggestion", "jor");
//
//HashMap<String, String> map1=new HashMap<String, String>();
//map1.put("email", "gefahiv654@devdigs.com");
//map1.put("password", "Hello1234*");
//map1.put("productName", "ADIDAS ORIGINAL");
//map1.put("countryName", "India");
//map1.put("suggestion", "ind");
