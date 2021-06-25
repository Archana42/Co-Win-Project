package stepDefinitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import ExcelFiles.Xls_Reader;

public class VaccineCenter {

	public static WebDriver driver;
	public static String findText(String xPath, int length) {
		
		List<WebElement> elements = driver.findElements(By.xpath(xPath));
		if(elements.size()!=0) {
			return  length==0?elements.get(0).getText():elements.get(0).getText().substring(length);
		}
		return "NA";
	}
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread.sleep(2000);

		// Javascript Executor acts as an interface between Selenium webdriver and UI Interface
		// like scrolling down through the page and for performing mouse related actions we use it.
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("https://www.cowin.gov.in/home");
		js.executeScript("window.scrollBy(0,900)");

		driver.findElement(By.xpath("//div[text()='Search by District']")).click();

		Actions builder = new Actions(driver);

		WebElement state=driver.findElement(By.cssSelector("#mat-select-0"));
		state.click();
		Thread.sleep(2000);
		WebElement selState = driver.findElement(By.xpath("//*[@id=\"mat-option-1\"]"));
		builder.moveToElement(selState).build().perform();
		Thread.sleep(2000);
		selState.click();

		Thread.sleep(2000);

		WebElement district=driver.findElement(By.xpath("//*[@id=\"mat-select-2\"]"));
		district.click();
		WebElement selDistrict = driver.findElement(By.xpath("//*[@id=\"mat-option-42\"]"));
		builder.moveToElement(selDistrict).build().perform();
		Thread.sleep(2000);
		selDistrict.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[text()='Search']")).click();
		Thread.sleep(2000);

		Xls_Reader reader = new Xls_Reader("src\\test\\java\\ExcelFiles\\Vaccine.xlsx");
		reader.addColumn("Vaccine", "Hospital Name");
		reader.addColumn("Vaccine", "Address");
		reader.addColumn("Vaccine", "Vaccine Type");
		reader.addColumn("Vaccine", "Dose1 Count");
		reader.addColumn("Vaccine", "Dose2 Count");
		reader.addColumn("Vaccine", "Total Count");
		reader.addColumn("Vaccine", "Vaccine Name");
		reader.addColumn("Vaccine", "Age");
		reader.addColumn("Vaccine", "Date");

		
		Thread.sleep(4000);
		List<WebElement> row = driver.findElements(By.xpath("/html/body/app-root/div/app-home/div[3]/div/appointment-table/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div/div[2]/div[7]/div/div/div/div"));	
		String hNameParent = "/html/body/app-root/div/app-home/div[3]/div/appointment-table/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div/div[2]/div[7]/div/div/div/div[";

		String hNameChild = "]/div/div/div[1]/div/h5";
		String hAddrChild = "]/div/div/div[1]/div/p";
		String vaccineTypeChild = "]/div/div/div[1]/div/div/ul/li/span";

		List<WebElement> col = driver.findElements(By.xpath("/html/body/app-root/div/app-home/div[3]/div/appointment-table/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div/div[2]/div[7]/div/div/div/div[2]/div/div/div[2]/ul/li"));
		String dosesParent = "/html/body/app-root/div/app-home/div[3]/div/appointment-table/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div/div[2]/div[7]/div/div/div/div[";
		
//		System.out.println("Row Count : " +row.size());
//		System.out.println("Col Count : " +col.size());

		String dose1Count = "]/div/div/div[2]/ul/li[";
		String dose1Count1="]/div[1]/div/div[1]/span[1]";
		String dose1Count2 ="]/div[2]/div/div[1]/span[1]";

		String totalCount  =  "]/div/div/div[2]/ul/li[";
		String totalCount1  = "]/div[1]/div/div[1]/a";
		String totalCount2  = "]/div[2]/div/div[1]/a";


		String dose2Count =  "]/div/div/div[2]/ul/li[";
		String dose2Count1 = "]/div[1]/div/div[1]/span[2]";
		String dose2Count2 = "]/div[2]/div/div[1]/span[2]";

		String vaccineName =  "]/div/div/div[2]/ul/li[";
		String vaccineName1 = "]/div[1]/div/div[2]/h5";
		String vaccineName2 = "]/div[2]/div/div[2]/h5";

		String age =  "]/div/div/div[2]/ul/li[";
		String age1 = "]/div[1]/div/div[3]/span";
		String age2 = "]/div[2]/div/div[3]/span";

		int rowCount =2;
		for(int j =1; j<=row.size(); j++) {

			int date =0;
			String hNameXpath = hNameParent+j+hNameChild;
			String hAddrXpath = hNameParent+j+hAddrChild;
			String vaccineTypeXpath = hNameParent+j+vaccineTypeChild;

			String hospitalName = driver.findElement(By.xpath(hNameXpath)).getText();
			String hospitalAddr = driver.findElement(By.xpath(hAddrXpath)).getText();
			String vaccineType = null;
			try{
				vaccineType = driver.findElement(By.xpath(vaccineTypeXpath)).getText();
			}
			catch(NoSuchElementException e) {
			}
			
			for(int k =1; k<=col.size(); k++) {
					
					String dose1CountXpath = dosesParent +j + dose1Count + k + dose1Count1;
					String dose2CountXpath = dosesParent +j + dose2Count + k + dose2Count1;
					String totalCountXpath = dosesParent +j + totalCount + k + totalCount1;
					String vaccineNameXpath = dosesParent +j + vaccineName + k + vaccineName1;
					String ageXpath = dosesParent + j + age + k + age1;

					String dose1CountXpath1 = dosesParent +j + dose1Count+ k +dose1Count2;
					String dose2CountXpath1 = dosesParent +j + dose2Count + k +dose2Count2;
					String totalCountXpath1 = dosesParent +j + totalCount + k +totalCount2;
					String vaccineNameXpath1 = dosesParent +j + vaccineName + k +vaccineName2;
					String ageXpath1 = dosesParent + j + age + k +age2;
					
					String dose1CntValue = findText(dose1CountXpath,3);
					String dose2CntValue = findText(dose2CountXpath,3);
					String totalCnt = findText(totalCountXpath,0);
					String vaccName = findText(vaccineNameXpath,0);
					String ageValue = findText(ageXpath,0);
					
					String dose1CntValue1 = findText(dose1CountXpath1,3);
					String dose2CntValue1 = findText(dose2CountXpath1,3);
					String totalCnt1 = findText(totalCountXpath1,0);
					String vaccName1 = findText(vaccineNameXpath1,0);
					String ageValue1 = findText(ageXpath1,0);
					
				
					// Sheetname Col Row Value
					reader.setCellData("Vaccine", "Hospital Name", rowCount , hospitalName);
					reader.setCellData("Vaccine", "Address", rowCount , hospitalAddr);
					reader.setCellData("Vaccine", "Vaccine Type", rowCount , vaccineType);
					
					reader.setCellData("Vaccine", "Dose1 Count", rowCount , dose1CntValue);
					reader.setCellData("Vaccine", "Dose2 Count", rowCount , dose2CntValue);
					reader.setCellData("Vaccine", "Total Count", rowCount , totalCnt);
					reader.setCellData("Vaccine", "Vaccine Name", rowCount, vaccName);
					reader.setCellData("Vaccine", "Age",rowCount , ageValue);
					
					List<WebElement> list = driver.findElements(By.xpath("/html/body/app-root/div/app-home/div[3]/div/appointment-table/div/div/div/div/div/div/div/div/div/div/div[2]/form/div/div/div[2]/div[6]/div/div/ul/carousel/div/div/slide"));
					String elementText = list.get(date).getText();

					reader.setCellData("Vaccine", "Date", (rowCount++), elementText);
						
					reader.setCellData("Vaccine", "Hospital Name", rowCount , hospitalName);
					reader.setCellData("Vaccine", "Address", rowCount , hospitalAddr);
					reader.setCellData("Vaccine", "Vaccine Type", rowCount , vaccineType);
					reader.setCellData("Vaccine", "Dose1 Count", rowCount , dose1CntValue1);
					reader.setCellData("Vaccine", "Dose2 Count", rowCount , dose2CntValue1);
					reader.setCellData("Vaccine", "Total Count", rowCount , totalCnt1);
					reader.setCellData("Vaccine", "Vaccine Name", rowCount , vaccName1);
					reader.setCellData("Vaccine", "Age", rowCount , ageValue1);
					
					reader.setCellData("Vaccine", "Date", (rowCount++), elementText);			
					date++;
			}	
		}
		driver.close();
	}
	
}
