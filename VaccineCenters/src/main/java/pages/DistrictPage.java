package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DistrictPage {

	// here we are using without pom structure so need to import PageFactory
	WebDriver driver;

	public DistrictPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how=How.XPATH, using="//div[text()='Search by District']")
	WebElement searchDistrictLink;	
	@FindBy(how=How.CSS, using="#mat-select-0")
	WebElement state;
	@FindBy(how=How.XPATH, using="//*[@id=\"mat-option-1\"]")
	WebElement selectState;
	@FindBy(how=How.XPATH, using="//*[@id=\"mat-select-2\"]")
	WebElement district;
	@FindBy(how=How.XPATH, using="//*[@id=\"mat-option-42\"]")
	WebElement selectDistrict;
	@FindBy(how=How.XPATH, using="//*[text()='Search']\"")
	WebElement searchButton;
	
	public void searchByDistrict() throws InterruptedException {
		
		searchDistrictLink.click();
		Actions builder = new Actions(driver);
		state.click();
		Thread.sleep(2000);
		WebElement selState = selectState;
		builder.moveToElement(selState).build().perform();
		Thread.sleep(2000);
		selState.click();

		district.click();
		Thread.sleep(2000);
		WebElement selDistrict = selectDistrict;
		builder.moveToElement(selDistrict).build().perform();
		Thread.sleep(2000);
		selDistrict.click();
		
		searchButton.click();		
	}
}
