package com.selenium.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

public class DriverFactory {
	private enum browsers {
		EXPLORER, FIREFOX, CHROME
	};

	public static WebDriver LevantarBrowser(WebDriver driver, ITestContext context) {
		String browserName = context.getCurrentXmlTest().getParameter("BrowserName");
		String url = context.getCurrentXmlTest().getParameter("URL");

		switch (browsers.valueOf(browserName)) {
		case CHROME: // Using WebDriver
		{
			System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
			Reporter.log("Abro browser");
			driver = new ChromeDriver();
			break;
		}
		case FIREFOX:// Using WebDriver
		{
			System.setProperty("webdriver.gecko.driver", "src\\resources\\geckodriver.exe");
			Reporter.log("Abro browser");
			driver = new FirefoxDriver();
			break;
		}
		default:
			Reporter.log("No selecciono ningun browser correcto, se le asignara Chrome");
			System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
			Reporter.log("Abro browser");
			driver = new ChromeDriver();
			break;

		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}
	
	public static void FinalizarBrowser(WebDriver driver) {
		Reporter.log("Cerrando el browser");
		driver.quit();
		driver = null;
	}
}
