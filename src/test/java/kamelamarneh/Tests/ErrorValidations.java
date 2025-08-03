package kamelamarneh.Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import kamelamarneh.TestComponents.BaseTest;
import kamelamarneh.TestComponents.Retry;
import kamelamarneh.pageobjects.MyCart;
import kamelamarneh.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {
	

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void GetErrorMessage() throws IOException, InterruptedException {
		
		
		
		
		Properties properties = new Properties();
		FileInputStream files = new FileInputStream("C:\\Users\\kamel\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\kamelamarneh\\resources\\GlobalVariables.properties");
		properties.load(files);
		
		String email = properties.getProperty("email");
		String password = properties.getProperty("password");

		landingPage.loginApplication("ka@ka.ka", password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorrMessage());
		System.out.println(landingPage.getErrorrMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		Properties properties = new Properties();
		FileInputStream files = new FileInputStream("C:\\Users\\kamel\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\kamelamarneh\\resources\\GlobalVariables.properties");
		properties.load(files);
		
		String email = properties.getProperty("email");
		String password = properties.getProperty("password");
		String productName = properties.getProperty("productName");

		ProductCatalog productCatalog = landingPage.loginApplication(email, password);
		productCatalog.addProductToCart(productName);
		MyCart myCart = productCatalog.cart();

//		Boolean match = myCart.getCartList(productName);
		Boolean match = myCart.getCartList("huguyutyuty");
		System.out.println(match);
		Assert.assertFalse(match);
	}

}
