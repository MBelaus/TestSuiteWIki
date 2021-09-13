package com.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class WikiContactoPage {
	@FindBy(id = "firstHeading")
	private WebElement f1;
	@FindBy(id = "Información_importante")
	private WebElement sub1;
	@FindBy(id = "Dudas_más_frecuentes")
	private WebElement sub2;
	@FindBy(className = "mw-redirect")
	private WebElement soli;
	@FindBy(id = "firstHeading")
	private WebElement titulo;

	public void titulo() {
		Reporter.log("Validar que el titulo sea el correcto");
		String titulo = "Wikipedia:Contacto";
		Assert.assertTrue(f1.getText().contains(titulo));
	}

	public void Subtitulo() {
		Reporter.log("Validar Subtitulos");
		String sub = "Información importante";
		Assert.assertTrue(sub1.getText().contains(sub));
	}

	public void Subtitulo1() {
		String Sub = "Dudas más frecuentes";
		Assert.assertTrue(sub2.getText().contains(Sub));

	}

	public void Solicitud() {
		Reporter.log("verificar que el boton solicitudes sea visible y Ver la pagina de solucitudes");
		Assert.assertTrue((soli.isDisplayed()));
		Reporter.log("Hacer Click en Solicitudes");
		soli.submit();
	}

	public void SolicitudTitulo() {
		Reporter.log("Verificar titulo de la pagina Solicitudes");
		String solici = "Wikipedia:Artículos solicitados";
		Assert.assertTrue(titulo.getText().contains(solici));
	}
}
