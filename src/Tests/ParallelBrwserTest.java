package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Resources.*;

public class ParallelBrwserTest
{
	public WebDriver wd;
	@Test
	@Parameters({"browername"})
	public void fblogintest(String bname)
	{
		String url="http://www.facebook.com";
		if (bname.equalsIgnoreCase("IE"))
		{
			System.out.println("Initialising "+bname+" driver");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			DesiredCapabilities dc=DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			wd=new InternetExplorerDriver(dc);
		}
		if (bname.equalsIgnoreCase("Chrome"))
		{
			System.out.println("Initialising "+bname+" driver");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			wd = new ChromeDriver();
		}
		if (bname.equalsIgnoreCase("Firefox"))
		{
			System.out.println("Initialising "+bname+" driver");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\geckodriver.exe");
	        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	        capabilities.setCapability("marionette", true);
	        wd = new FirefoxDriver(capabilities);
		}
		System.out.println("I am in test method:-"+Thread.currentThread().getName());
		wd.get(url);
		getobject goobj=new getobject();
		wd.findElement(goobj.getemailobj()).sendKeys("abc@gmail.com");
		wd.findElement(goobj.getpassobj()).sendKeys("pass");
		wd.findElement(goobj.getbtnobj()).click();
	}
	@AfterMethod
	public void aftermethtest()
	{
		System.out.println("I am in after test "+Thread.currentThread().getName());
		wd.quit();
	}
}
