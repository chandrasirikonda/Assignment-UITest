package com.automationui.basetest;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {
	protected WebDriver driver = null;
	protected Properties config = null;
	protected Properties OR = null;

	protected void loadConfigData() throws Exception {

		if (driver == null) {
			config = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\config\\Config.properties");
			config.load(fis);

			OR = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\config\\OR.properties");
			OR.load(fis);
			if (config.getProperty("browser").equals("Chrome"))
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (config.getProperty("browser").equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		} else if (config.getProperty("browser").equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");

			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser type is not matched or configured ");
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	protected WebElement getObject(String xpathKey) {
		try {

			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));

		} catch (Throwable t) {
			
			System.out.println(t.getMessage());
		}
		return null;

	}

	protected void Close() {
		if (!(driver == null)) {
			driver.close();
		}
	}

	protected void Quit() {
		if (!(driver == null)) {
			driver.quit();

		}

	}
}