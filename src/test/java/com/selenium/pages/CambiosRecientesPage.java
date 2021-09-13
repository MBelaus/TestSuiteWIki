package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class CambiosRecientesPage {
	WebDriver driver;
	
	@FindBy(xpath = "//span[@class='oo-ui-iconElement-icon oo-ui-icon-menu']")
	private WebElement menu;
	@FindBy(xpath = "//input[@id='ooui-5']")
	private WebElement fn1;
	@FindBy(xpath = "//input[@id='ooui-6']")
	private WebElement fn2;
	
	public CambiosRecientesPage(WebDriver ldriver) {
		driver = ldriver;
	}

	public void BorrarFiltro(String filtro) {
		// hacer click en borra x filtro
		Reporter.log("Borrado de filtro '" + filtro + "'");
		String xpathLocator = String.format("//a[@title='%s']", filtro);
		WebElement f1 = driver.findElement(By.xpath(xpathLocator));
		f1.click();
	}
	
	public void AgregarFiltros() {
		
		menu.click();
		Reporter.log("Abrir menu");

		// Muy probablemente buenas
		
		Reporter.log("Agregar filtro 'Muy probablemente buenas'");
		fn1.click();

		// Pueden tener problemas
		Reporter.log("Agregar filtro 'Pueden tener problemas'");
		fn2.click();
	}
}
