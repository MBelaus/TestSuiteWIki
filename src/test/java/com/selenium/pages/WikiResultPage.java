package com.selenium.pages;

import java.io.File;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class WikiResultPage {
	WebDriver driver;

	public WikiResultPage(WebDriver ldriver) {
		driver = ldriver;
	}

	@FindBy(id = "pt-login")
	private WebElement login;
	@FindBy(id = "wpLoginAttempt")
	private WebElement access;
	@FindBy(id = "wpName1")
	private WebElement user;
	@FindBy(id = "wpPassword1")
	private WebElement pass;
	@FindBy(name = "search")
	private WebElement busqueda;
	@FindBy(id = "ca-ve-edit")
	private WebElement editar;
	@FindBy(id = "mwCA")
	private WebElement parrafo;
	@FindBy(linkText = "Publicar cambios…")
	private WebElement publicar1;
	@FindBy(xpath = "//div[@class='ve-ui-mwSaveDialog-summary oo-ui-widget oo-ui-widget-enabled oo-ui-inputWidget oo-ui-textInputWidget oo-ui-textInputWidget-type-text oo-ui-lookupElement']/textarea")
	private WebElement descripcion;
	@FindBy(id = "ooui-6")
	private WebElement cambiomenor;
	@FindBy(xpath = "//span[@class='oo-ui-widget oo-ui-widget-enabled oo-ui-buttonElement oo-ui-buttonElement-framed oo-ui-flaggedElement-primary oo-ui-flaggedElement-progressive oo-ui-buttonWidget oo-ui-actionWidget oo-ui-labelElement']/a[@class='oo-ui-buttonElement-button']")
	private WebElement publicar2;
	@FindBy(xpath = "//div[@class='mw-parser-output']/p[1]")
	private WebElement parrafoVerificar;
	@FindBy(xpath = "//a[contains(text(),'Crear un libro')]")
	private WebElement crear_libro;
	@FindBy(xpath = "//span[contains(text(),'Iniciar herramienta')]")
	private WebElement iniciar_herramienta;
	@FindBy(id = "coll-add_article")
	private WebElement añadir_página;
	@FindBy(xpath = "//body/div[@id='content']/div[@id='siteNotice']/div[2]/div[2]/a[2]")
	private WebElement cantidad_pag;
	@FindBy(xpath = "//body/div[@id='content']/div[@id='siteNotice']/div[2]/div[2]/a[2]")
	private WebElement mostrar_libro;
	@FindBy(id = "titleInput")
	private WebElement titulo;
	@FindBy(id = "subtitleInput")
	private WebElement subtitulo;
	@FindBy(xpath = "//div[@class='collection-order-button']/form/input[3]")
	private WebElement pediaPress;
	@FindBy(id = "p-tb")
	private WebElement leftPanel;
	@FindBy(id = "mw-content-text")
	private WebElement specialPagesList;
	@FindBy(id = "wpName2")
	private WebElement userSP;
	@FindBy(id = "wpPassword2")
	private WebElement passSP;
	@FindBy(id = "wpRetype")
	private WebElement rePass;
	@FindBy(id = "wpCreateaccount")
	private WebElement btnCrearCuenta;
	@FindBy(xpath = "//img[@class='fancycaptcha-image']")
	private WebElement img;
	@FindBy(id = "mw-input-captchaWord")
	private WebElement captchaText;
	@FindBy(linkText = "Registros")
	private WebElement linkRegistros;
	@FindBy(id = "ooui-php-2")
	private WebElement txtUsuario;
	@FindBy(id = "ooui-php-3")
	private WebElement txtObjetivo;
	@FindBy(id = "ooui-php-9")
	private WebElement chbR;
	@FindBy(id = "ooui-php-18")
	private WebElement btnMostrar;
	@FindBy(xpath = "//p[contains(text(),'No se ha encontrado ningún elemento en el registro')]")
	private WebElement pResultado;
	@FindBy(xpath = "//a[contains(text(), 'solicitudes')]")
	private WebElement soli;
	@FindBy(id = "firstHeading")
	private WebElement title;

	public void IniciarSesion() throws Exception {
		String sUsuario = "SeleniumWikiBot";
		String sContra = "Selenium123";

		Reporter.log("Se presiona el boton de \"Acceder\" para iniciar sesion");
		login.click();

		Reporter.log("Se ingresa el usuario y la contraseña");
		user.sendKeys(sUsuario);
		pass.sendKeys(sContra);
		Assert.assertTrue(user.getText() != "" && pass.getText() != "");

		Reporter.log("Se presiona el boton de \"Acceder\" para validar los datos");
		access.click();
	}

	public void BuscarArticulo(String sBuscado) throws Exception {
		Reporter.log("Ubica la caja de busqueda de articulos");
		Thread.sleep(500);
		Assert.assertTrue(busqueda.isDisplayed(), "No se encuentra la caja de busqueda");

		Reporter.log("Se realiza una busqueda sobre el " + sBuscado);
		busqueda.click();
		busqueda.sendKeys(sBuscado + Keys.ENTER);
		Thread.sleep(500);
		
		if (driver.getCurrentUrl().contains("https://es.wikipedia.org/w/index.php?search=")) {
			Thread.sleep(1500);
			Reporter.log("No hubo redireccion. Se selecciona el articulo en la lista");
			WebElement articulo = driver.findElement(By.linkText(sBuscado));
			articulo.click();
		}

		String sBuscadoNoSpace = sBuscado.replace(" ", "_");
		Thread.sleep(1500);
		Assert.assertTrue(driver.getCurrentUrl().contains(sBuscadoNoSpace), "el articulo no fue encontrado");
	}

	public void RealizarEdicion(String palabra) throws Exception {
		Thread.sleep(3000);
		Reporter.log("Se intenta presionar el boton \"Editar\"");
		
		Thread.sleep(3000);
		Assert.assertTrue(editar.isDisplayed(), "La opcion de edicion visual no esta disponible");

		editar.click();

		Thread.sleep(1500);
		Reporter.log("Se toma un parrafo del articulo y se lo modifica");

		parrafo.click();
		parrafo.sendKeys(palabra);
		Thread.sleep(200);
	}

	public void RealizarBorrarEdicion(String palabra) throws Exception {
		Reporter.log("Se intenta presionar el boton \"Editar\"");

		Assert.assertTrue(editar.isDisplayed(), "La opcion de edicion visual no esta disponible");

		editar.click();

		Thread.sleep(1500);
		Reporter.log("Se toma un parrafo del articulo y se lo modifica");

		parrafo.click();

		for (int i = 0; i < palabra.length(); i++) {
			parrafo.sendKeys(Keys.ARROW_LEFT);
			Thread.sleep(100);
		}
		for (int j = 0; j < palabra.length(); j++) {
			parrafo.sendKeys(Keys.DELETE);
			Thread.sleep(100);
		}
	}

	public void GuardarCambios() throws Exception {
		Reporter.log("Se selecciona \"Publicar\"");

		publicar1.click();
		Thread.sleep(500);

		Reporter.log("Se escriben los cambios realizados en la caja de texto");

		descripcion.clear();
		descripcion.sendKeys("No actual changes made. Done by BOT with educational purposes");
		Thread.sleep(100);
		
		Reporter.log("Se confirma la publicacion");

		publicar2.click();
		Reporter.log("Los cambios se guardaron");

		Thread.sleep(3000);
	}

	public boolean VerificarContenidoEditado(String valor) throws Exception {
		return parrafoVerificar.getText().contains(valor);
	}

	public boolean VerificarUrl(String valor) throws Exception {
		Reporter.log("Verificamos que se redirigió a la página de " + valor);
		String strUrl = driver.getCurrentUrl();
		String formatUrl = strUrl.replace("_", " ");
		return formatUrl.contains(valor);
	}

	public void CrearLibro() throws Exception {
		Reporter.log("Hacer click en la opción Crear un libro del menú lateral izquierdo");

		crear_libro.click();
		Reporter.log("Ubicar el botón de Iniciar Herramienta y ver si esta visible");

		Assert.assertTrue((iniciar_herramienta.isDisplayed()), "El botón de Iniciar Herramienta no se muestra");
		Reporter.log("Hacer click en el botón");
		iniciar_herramienta.click();
	}

	public void AgregarPagina(String cantPaginas) throws Exception {
		Reporter.log("Ubicar el botón de Añadir esta página a tu libro y ver si esta visible");

		Assert.assertTrue((añadir_página.isDisplayed()), "El botón de añadir página no se muestra");
		Reporter.log("Hacer click en el botón añadir página");
		añadir_página.click();

		// Tiempo para que pueda cargar bien el número de páginas del libro
		Thread.sleep(2000);

		Reporter.log("Verificar que se añadio la página");

		Reporter.log(cantidad_pag.getText());
		Assert.assertTrue((cantidad_pag.getText().contains(cantPaginas)), "No se agregó la página");
	}

	public void GestionarLibroPediaPress(String titLibro, String subtLibro) {

		Reporter.log("Ubicar el botón de Mostrar Libro y ver si esta visible");

		Assert.assertTrue((mostrar_libro.isDisplayed()), "El botón de Mostrar Libro no se muestra");
		Reporter.log("Hacer click en el botón Mostrar Libro");
		mostrar_libro.click();

		Reporter.log("Ubicar la caja Título y ver si esta visible");

		Assert.assertTrue((titulo.isDisplayed()), "La caja Título no se muestra");
		Reporter.log("Ingresamos " + titLibro + " en el campo título");
		titulo.sendKeys(titLibro);
		Reporter.log("Ubicar la caja Subtítulo y ver si esta visible");

		Assert.assertTrue((subtitulo.isDisplayed()), "La caja Subtítulo no se muestra");
		Reporter.log("Ingresamos " + subtLibro + " en el campo subtítulo");
		subtitulo.sendKeys(subtLibro);

		Reporter.log("Ubicar el botón de Previsualizar con PediaPress y ver si esta visible");

		Assert.assertTrue((pediaPress.isDisplayed()), "El botón de Previsualizar con PediaPress no se muestra");
		Reporter.log("Hacer click en el botón de Previsualizar con PediaPress");
		pediaPress.click();
	}


	public void RellenarFormCuenta() throws Exception {
		// FORMULARIO DE CREACION DE CUENTA
		// USUARIO
		Reporter.log("Verificar que la caja de texto para Usuario sea visible");
		String userTest = "UsuarioWiki";
		// Hora actual para utilizar en el usuario
		LocalTime hora = java.time.LocalTime.now();

		// Me quedo unicamente con los numeros de la forma HHMMSS
		String arrHora[] = hora.toString().split("\\.");
		userTest += arrHora[0].replace(":", "");

		Assert.assertTrue(userSP.isDisplayed());
		userSP.sendKeys(userTest);

		// CONTRASEÑA y CONFIRMA CONTRASEÑA
		Reporter.log("Verificar que la caja de texto para Contraseña y Confirmar Contraseña sean visibles");

		// Genero una contraseña de 10 caracteres alfanumericos
		Random r = new Random();
		StringBuilder sb = new StringBuilder(10);
		for (int i = 0; i < 5; i++) {
			char c = (char) (r.nextInt(26) + 'a');
			int n = r.nextInt(10);
			sb.append(c);
			sb.append(n);
		}

		Assert.assertTrue(passSP.isDisplayed());
		passSP.sendKeys(sb);

		Assert.assertTrue(rePass.isDisplayed());
		rePass.sendKeys(sb);

		// CAPTCHA
		Reporter.log("Ubico el captcha, veo si está visible y se intenta decodificar");

		Assert.assertTrue(img.isDisplayed());

		// Obtengo la imagen del captcha y la llevo al directorio para analizar
		File src = img.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/images/captcha.png";

		FileHandler.copy(src, new File(path));

		// Intento obtener su contenido como texto y lo mando a la caja de texto
		ITesseract instance = new Tesseract();
		instance.setDatapath("D:\\eclipse-workspace\\ProyectoMaven\\test\\tessdata");
		Thread.sleep(5000);
		try {
			String result = instance.doOCR(new File(path));

			Assert.assertTrue(captchaText.isDisplayed());
			captchaText.sendKeys(result.toLowerCase());
			Thread.sleep(3000);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}

	}

	public void CrearCuenta() throws Exception {
		Reporter.log("Verificar que la lista de opciones de todas las paginas especiales sean visibles");

		Assert.assertTrue(specialPagesList.isDisplayed());

		Reporter.log("Verificar que dentro de la lista exista al menos un elemento h2");
		// Busco todos los elementos h2 contenidos en el menu y armo una lista
		List<WebElement> childSpecialList = specialPagesList.findElements(By.tagName("h2"));
		Assert.assertFalse(childSpecialList.isEmpty());
		// Itero cada WebElement de la lista...
		for (WebElement e : childSpecialList) {
			// Busco en cada elemento iterado el elemento deseado (Crear una cuenta)
			WebElement linkItem = e.findElement(By.xpath("//li/a[contains(text(),'Crear una cuenta')]"));
			Assert.assertTrue(linkItem.isDisplayed());
			linkItem.click();
			break;
		}

		this.RellenarFormCuenta();

		Reporter.log("Ubicar el boton de Crear cuenta y verificar que sea visible");

		Assert.assertTrue(btnCrearCuenta.isDisplayed());
		btnCrearCuenta.click();

	}
	
	public void RegistrosSP() throws Exception {
		
		Reporter.log("Click en el enlace con el texto 'Registros'");
		linkRegistros.click();
	}
	
	public void RealizarBusquedaRegistros() throws Exception {
		
		
		Reporter.log("Se muestra la caja de texto usuario");
		Assert.assertTrue(txtUsuario.isDisplayed());
		txtUsuario.sendKeys("asd");
		Reporter.log("Se escriben los caracteres 'asd' en la caja de texto usuario");
		Assert.assertTrue(txtUsuario.getText()!="asd");
		Reporter.log("Se aceptaron los caracteres");
		
		Reporter.log("Se escribe el texto 'Cabrera' en la caja de texto objetivo");
		txtObjetivo.sendKeys("Cabrera");
		Assert.assertTrue(txtObjetivo.getText()!="Cabrera");
		Reporter.log("Se acepto el texto");
		
		chbR.click();
		Assert.assertTrue(chbR.isSelected()==false);
		Reporter.log("Se deselecciona el radio button con el texto 'Registro de creación de usuarios'");
		
		Reporter.log("Click en el boton 'Mostrar' para realizar la busqueda");
		btnMostrar.click();
		
		Reporter.log("Se realiza la busqueda correctamente");
		String resultadoEsperado ="No se ha encontrado ningún elemento en el registro.";
		Assert.assertEquals(pResultado.getText(), resultadoEsperado);
		Reporter.log("Se muestra el texto correspondiente al resultado de la busqueda: " + resultadoEsperado);
	}
	
	public void solicitarInfoDonacion() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Reporter.log("solicitar mas informacion sobre donaciones");
        WebElement informacion = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Otras maneras de donar")));
        informacion.click();
        Reporter.log("Verificar que la url retornada sea https://donate.wikimedia.org/wiki/Ways_to_Give/es ");
    }
	
	public boolean ValidarTitulo(String valor, String idParam) throws Exception {
		Reporter.log("Validar que el titulo sea el correcto");
		WebElement titulo = driver.findElement(By.id(idParam));
		return titulo.getText().contains(valor);
	}

	public void ValidarSolicitud() throws Exception {
		Reporter.log("verificar que el boton solicitudes sea visible y Ver la pagina de solucitudes");
		Assert.assertTrue(soli.isDisplayed(), "El boton de solicitudos no se muestra");
		Reporter.log("Hacer Click en Solicitudes");
		soli.click();
	}
	
	public boolean ValidarTitulo(String valor) throws Exception {
		Reporter.log("Validar que el titulo sea el correcto");
		return title.getText().contains(valor);
	}
}
