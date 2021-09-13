package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WikiHomePage {
	WebDriver driver;

	public WikiHomePage(WebDriver ldriver) {
		driver = ldriver;
	}

	@FindBy(id = "js-link-box-es")
	private WebElement idioma;
	@FindBy(id = "searchInput")
	private WebElement caja;
	@FindBy(id = "searchLanguage")
	private WebElement languageCombo;
	@FindBy(xpath = "//select[@id='searchLanguage']/option[18]")
	private WebElement espCombo;
	@FindBy(xpath="//span[contains(text(), 'Contacto')]")
    private WebElement contacto;
	

	public void AbrirPagina() throws Exception {
		Reporter.log("Ubica y selecciona el idioma Español");
		Assert.assertTrue(idioma.isDisplayed(), "El idioma no aparece.");
		idioma.click();
	}
	
	public void AbrirPagina(String lenguaje) throws Exception {
		Reporter.log("Ubica y selecciona el idioma " + lenguaje);
		String lenguajeElegido = String.format("js-link-box-%s", lenguaje);
		WebElement idiomaVariable = driver.findElement(By.id(lenguajeElegido));
		Assert.assertTrue(idiomaVariable.isDisplayed(), "El idioma no aparece.");
		idiomaVariable.click();
	}

	public void RealizarBusqueda(String busqueda) throws Exception {
		Reporter.log("Ubicar la caja de texto de buscar y ver si esta visible");
		
		Assert.assertTrue((caja.isDisplayed()), "La caja de texto no se muestra");
		Reporter.log("Ingresamos la palabra " + busqueda);
		caja.sendKeys(busqueda);

		Reporter.log("Selecciono idioma Español en el combo");
		languageCombo.click();
		espCombo.click();

		Reporter.log("Presionamos enter del teclado");
		caja.sendKeys(Keys.ENTER);
	}
	
	public void OtherProjects(String project) throws Exception {
		WebDriverWait driverWait = new WebDriverWait(driver, 10);
		Reporter.log("Hacer click en el boton " + project);
		String xpathLocator = String.format("//span[normalize-space()='%s']", project);
		WebElement boton1 =driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
		Assert.assertTrue((boton1.isDisplayed()));
		boton1.click();
	}

    public void BotonContacto() {
        Reporter.log("Buscar el boton de Contacto y validar que exista");
        Assert.assertTrue((contacto.isDisplayed()));
        Reporter.log("Hacer Click");
        contacto.submit();
    }
}
