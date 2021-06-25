package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

public class districtStepDef {

	WebDriver driver = null;
	
	@Given("Browser is open")
	public void browser_is_open() {
	
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project Path is : " + projectPath);
		
		System.out.println("Inside Step - Browser is Open");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@And("User is on Home Page")
	public void user_is_on_home_page() {
		
		System.out.println("User is on Co-WIN Page");
		driver.navigate().to("https://www.cowin.gov.in/home");
	}

	@When("User clicks on Search by district")
	public void user_clicks_on_search_by_district() {

	}

	@And("User selects state Andhra Pradesh and district Kurnool")
	public void user_selects_state_andhra_pradesh_and_district_kurnool() {

	}

	@Then("Vaccine centers slots will be displayed as per inputs")
	public void vaccine_centers_slots_will_be_displayed_as_per_inputs() {

	}
}
