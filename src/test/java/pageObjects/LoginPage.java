package pageObjects;

//import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.internal.selenesedriver.GetElementAttribute;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage{
	final private WebDriver driver;
	
	@FindBy(name="user")
	private WebElement in_user;
	
	@FindBy(name="pwd")
	private WebElement in_pwd;
	
	@FindBy(id ="btsignin")
	private WebElement entrar;
	
	@FindBy(xpath="/html/body/main/div/header/hgroup/h2")
	private WebElement cabecera;
	
	 
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public void completaLogin(String email, String pass){
		in_user.sendKeys(email);
		in_pwd.sendKeys(pass);

	}
	

	public LoginPage acceder(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(entrar));
		entrar.click();
		return this;		
	}
	
	
	public String compruebaEntrada(){

		String actual = "";
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(cabecera));
		actual = getResultado();
		return actual;
		
	}
	
	public String getResultado() {
		WebDriverWait waitDriver = new WebDriverWait(driver, 1);
		waitDriver.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return cabecera.getAttribute("class").equalsIgnoreCase("loading") ? false : true;
			}
		});
		String valor = cabecera.getText();
		return valor;
	}
	
	public String compruebaError(){
		
		String actual = "";
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(entrar));
		actual = getResultadoErr();
		return actual;
	}
	
	public String getResultadoErr() {
		WebDriverWait waitDriver = new WebDriverWait(driver, 1);
		waitDriver.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return entrar.getAttribute("class").equalsIgnoreCase("loading") ? false : true;
			}
		});
		String valor = entrar.getText();
		return valor;
	}
}