package Frameworks.DataDrivenFramework;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class MeddoApointmentBooking {
WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    @BeforeTest
public void initialization(){
    // To set the path of the Firefox driver.
System.setProperty("webdriver.chrome.driver", "C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\driver\\Chromedriver.exe");
driver = new ChromeDriver();


    // To launch Meddo website
    driver.get("https://www.meddo.in/");
    // To maximize the browser
    driver.manage().window().maximize();
    // implicit wait
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
  
@Test
public void Meddoonlinebooking() throws IOException{
// Import excel sheet.
File src=new File("C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\Excel\\TestData.xlsx");   
// Load the file.
FileInputStream fis = new FileInputStream(src);
// Load the workbook.
workbook = new XSSFWorkbook(fis);
// Load the sheet in which data is stored.
sheet= workbook.getSheetAt(0);
for(int i=1; i<=sheet.getLastRowNum(); i++){
// Import data for Email.
cell = sheet.getRow(i).getCell(0);
/*cell.setCellType(Cell.CELL_TYPE_STRING);*/

//Click on Login link
driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();

//Click on sign with mobile number button
driver.findElement(By.xpath("//*[contains(text(),'Sign in with Mobile Number')]")).click();

//Enter the Mobile number
//driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("9591008397");

//Click on sign in with email link
driver.findElement(By.xpath("//*[contains(text(),'E-mail instead')]")).click();

//Enter Username
driver.findElement(By.xpath("//input[@id='username']")).sendKeys(cell.getStringCellValue());

//Import data for password.
cell = sheet.getRow(i).getCell(1);
/*cell.setCellType(Cell.CELL_TYPE_STRING);*/

//Enter Password
driver.findElement(By.xpath("//input[@id='password']")).sendKeys(cell.getStringCellValue());

//click on sign in button
driver.findElement(By.xpath("//*[contains(text(),'Sign In')]")).click();
System.out.println("User clicked on sign in link");

try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//Click on visit a doctor link
driver.findElement(By.xpath("//*[contains(text(),'Visit A Doctor')]")).click();

//Click on Book an Appointment
driver.findElement(By.xpath("//*[contains(text(),'BOOK APPOINTMENT')]")).click();

try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

//Click on online appointment
driver.findElement(By.xpath("//*[contains(text(),'Online consultation')]")).click();


driver.findElement(By.xpath("//span[contains(text(),'11:00 AM')]")).click();

//driver.findElement(By.xpath("//*[contains(text(),'BOOK APPOINTMENT')]")).click();

//Clicks on Logout button.
driver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();


//To close the Browser
     
driver.close();

}
}
}