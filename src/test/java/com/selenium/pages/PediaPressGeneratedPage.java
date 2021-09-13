package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class PediaPressGeneratedPage {
	
	WebDriver driver;

	public PediaPressGeneratedPage(WebDriver ldriver) {
		driver = ldriver;
	}
	
	@FindBy(id = "num_pages")
	private WebElement paginas;
	@FindBy(className = "cartbtn")
	private WebElement add;
	
	public boolean VerificarUrl(String valor) throws Exception {
		Reporter.log("Verificamos que se redirigió a la página de " + valor);
		Thread.sleep(10000);
		return driver.getCurrentUrl().contains(valor);
	}
	
	public void AddCart(String cantPags) throws Exception {
		Reporter.log("Verificar que son " + cantPags + " páginas");
		
		Assert.assertTrue((paginas.getText().contains(cantPags)), "El número de páginas es erroneo");

		Reporter.log("Ubicar el botón de Add to Cart y ver si esta visible");
		
		Assert.assertTrue((add.isDisplayed()), "El botón Add to Cart no se muestra");
		Reporter.log("Hacer click en el botón Add To Cart");
		add.click();
	}

}
