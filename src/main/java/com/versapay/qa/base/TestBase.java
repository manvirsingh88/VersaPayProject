package com.versapay.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.versapay.qa.utils.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
    public static FileInputStream fis;
    public static Properties pro;
    public static Logger logger;//from log4j
    public static EventFiringWebDriver e_driver; // from selenium for WebFiringEvents
    public static WebEventListener eventListener;// from Utils for WebFiringEvents
    
    
    
	public TestBase() {
		pro = new Properties();
		try {
			fis = new FileInputStream("./src\\main\\java\\com\\versapay\\qa\\configuration\\Config.properties");
			pro.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @BeforeClass
	public void logerSetUp()
	{
		logger = Logger.getLogger(TestBase.class);
 		//DOMConfigurator.configure("log4j.xml");
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
	}
	
	
	public void  initialisation() {
		String browserName = pro.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
	}
		else {
			System.out.println("Browser Not Avaliable");
		}
		
		
		//for firing WebEvents
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(pro.getProperty("url"));
		
	}
	
}
