package es.pruebas;

import java.util.concurrent.TimeUnit;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import pageObjects.LoginPage;

public class TestOK {
	private WebDriver driver;
	//Properties
	/**Creamos un Objeto de tipo Properties*/
	Properties propiedades = new Properties();
	InputStream entrada=null;
	
	@Before
	public void configurar() {
		try {

			/*Se asigna la configuración del driver
			Para el caso de Chrome tenemos que instalar el Driver*/
			System.setProperty("webdriver.chrome.driver", "C:\\DESARROLLO\\drivers\\chromedriver.exe");
			this.driver = new ChromeDriver();
			//Para usar el navegador Firefox, usado por defecto 
			//driver = new FirefoxDriver();

			// Configuramos el tiempo implicito de espera para localizar los elementos
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			// Maximizamos la ventana del navegador
			driver.manage().window().maximize();
			
			entrada = new FileInputStream("valores.properties");

		    //Se carga el archivo de propiedades
		    propiedades.load(entrada);

			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("ERROR EN LA CONFIGURACION");
		}
	}

	@Test
	/*Accedo al login con credenciales correctos*/
	
	public void testTest() {
		try {
						
			String actual ="";
			String url = propiedades.getProperty("direccionWeb", "valoresComunes.properties");
			String usuario = propiedades.getProperty("loginUsuario", "valoresComunes.properties");
			String password = propiedades.getProperty("loginPassword", "valoresComunes.properties");
			String pantallaOk = propiedades.getProperty("pantallaOK", "valoresComunes.properties");

			//Acceso a Web 
			driver.get(url);			
			
			//Vista de Login
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			login.completaLogin(usuario, password);
			login.acceder();
			
			//Comprobación vista actual
			actual = login.compruebaEntrada();
			Assert.assertEquals(pantallaOk, actual);
			
					
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("TEST FALLIDO");
		}
	}

	@After
	public void finalizar() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
