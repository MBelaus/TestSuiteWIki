package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class WikiContactoPage {
	WebDriver driver;

	public WikiContactoPage(WebDriver ldriver) {
		driver = ldriver;
	}

	@FindBy(xpath = "//a[contains(text(), 'solicitudes')]")
	private WebElement soli;

	public void ValidarTitulo(String valor, String idParam) throws Exception {
		Reporter.log("Validar que el titulo sea el correcto");
		WebElement titulo = driver.findElement(By.id(idParam));
		Assert.assertTrue(titulo.getText().contains(valor), valor + " no se muestra");
	}

	public void Solicitud() throws Exception {
		Reporter.log("verificar que el boton solicitudes sea visible y Ver la pagina de solucitudes");
		Assert.assertTrue(soli.isDisplayed(), "El boton de solicitudos no se muestra");
		Reporter.log("Hacer Click en Solicitudes");
		soli.click();
	}

}
