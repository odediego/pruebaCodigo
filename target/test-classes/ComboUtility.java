import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public clickcombo(String combo,String opcion){
	//Le enviamos el id del combo por XPATH
	WebElement clicacombo = driver.findElement(By.xpath(combo));
	clicacombo.click();
	//Se selecciona la opcion
	WebDriverWait waitRes = new WebDriverWait(driver, 5);
	WebElement resifiscal = waitRes.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(opcion)));
	resifiscal.click();
}