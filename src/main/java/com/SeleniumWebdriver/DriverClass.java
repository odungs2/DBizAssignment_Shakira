package com.SeleniumWebdriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class DriverClass {
	protected static WebDriver driver;
	public static Properties envConfig;
	public long waitTime = 30;
	private static final Logger log = LogManager.getLogger(DriverClass.class);
	public static final String ENV = System.getProperty("env", "Production");

	@BeforeSuite
	public void Setup() throws IOException, ParseException, InterruptedException {
		File file = new File("Binaries" + File.separator + "chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		log.info("Chrome Driver is initialized");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://finance.yahoo.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		log.info("Url Loaded");

	}

	@AfterSuite
	public void exit() {
		System.out.println("test done");
		//driver.quit();
	}
	}
