package com.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WikiMainPage {
	WebDriver driver;
	
	public WikiMainPage(WebDriver ldriver) {
		driver = ldriver;
	}
	
	@FindBy(id = "p-tb")
	private WebElement leftPanel;
	
	public void SeleccionarOpcionMenuLateral(String valor) {
		String xpathLocator = String.format("//li/a[contains(text(),'%s", valor); 
		xpathLocator+= "')]";
		Reporter.log("Verificar que el panel izquierdo de opciones sea visible");

		Assert.assertTrue(leftPanel.isDisplayed());

		Reporter.log("Verificar que dentro del panel exista al menos un elemento h3");
		// Busco todos los elementos h3 contenidos en el panel izquierdo y armo una
		// lista
		List<WebElement> childList = leftPanel.findElements(By.tagName("h3"));
		Assert.assertFalse(childList.isEmpty());
		// Itero cada WebElement de la lista...
		for (WebElement e : childList) {
			// Busco en cada elemento iterado el elemento deseado (Paginas especiales)
			WebElement linkItem = e.findElement(By.xpath(xpathLocator));
			Assert.assertTrue(linkItem.isDisplayed());
			linkItem.click();
			break;
		}
	}
	
	public boolean VerificarUrl(String valor) throws Exception{
		Reporter.log("Verificamos que se redirigió a la página de " + valor);
		String strUrl = driver.getCurrentUrl();
		return strUrl.contains(valor);
	}
	
	public void UbicarOpcionDonaciones() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Reporter.log ("Ubicar donaciones y ver si esta disponible");
        WebElement btnDonaciones = driver.findElement(By.linkText("Donaciones"));
        Assert.assertTrue(btnDonaciones.isDisplayed(),"El boton no aparece");
        WebElement donacion = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Donaciones")));
        donacion.click();
        Reporter.log("Verificar que la url retornada sea https://donate.wikimedia.org/w/index.php?title=Special:LandingPage&country=AR&uselang=es&utm_medium=sidebar&utm_source=donate&utm_campaign=C13_es.wikipedia.org ");
        Assert.assertTrue (driver.getCurrentUrl().contains("donate.wikimedia.org/w/index.php?title=Special:LandingPage&country=AR&uselang=es&utm_medium=sidebar&utm_source=donate&utm_campaign=C13_es.wikipedia.org"));
    }
}
