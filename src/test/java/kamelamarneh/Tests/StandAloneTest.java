package kamelamarneh.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import kamelamarneh.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage= new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("noritof738@coursora.com");

		driver.findElement(By.id("userPassword")).sendKeys("Hello1234*");

		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match= cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("jor");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> options= driver.findElements(By.cssSelector(".ta-results"));
		for(WebElement option :options) {
			if(option.getText().contains("Jordan"))
			{
				option.click();
				break;
			}
		}
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		
		String orderId= driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText().replaceAll("[|\\s]", "");
		System.out.println(orderId);

	}

}
