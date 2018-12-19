
package com.automationui.test;

import org.fluttercode.datafactory.impl.DataFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationui.basetest.BaseTest;

public class Testcase extends BaseTest {
	DataFactory df = new DataFactory();
	

	@BeforeTest
	public void CreateUser() throws Exception {
		loadConfigData();

	}

	@Test(dataProvider = "userdetails")
	public void createuser(String Username, String password, String email) {
		driver.get(config.getProperty("url"));
		getObject("Users").click();
		getObject("newuser").click();
		String userpage="Active Admin Demo";
		Assert.assertEquals(getObject("Demotext").getText(), userpage);
		getObject("Username").sendKeys(df.getFirstName());
		getObject("password").sendKeys(df.getFirstName());
		getObject("email").sendKeys(df.getEmailAddress());
		getObject("submitbutton").click();

	}

	@Test
	public void filtersearch() {
		driver.get(config.getProperty("url"));
		getObject("Users").click();
		String title="Users";
		Assert.assertEquals(getObject("userstitle").getText(), title);
		getObject("clearfilters").click();
		getObject("usernamedropdown").sendKeys("Contains");
		getObject("Usernametext").sendKeys("test");
		getObject("Emaildropdown").sendKeys("Contains");
		getObject("Emailtext").sendKeys("test.com");
		getObject("createdate").sendKeys("2018-11-20");
		getObject("Todate").sendKeys("2018-12-12");
		getObject("Filterbutton").click();
	}

	@DataProvider(name = "userdetails")
	public Object[][] getDataFromDataprovider() {
		return new Object[][]

		{ { df.getFirstName(), df.getLastName(), df.getEmailAddress() },
		  { df.getFirstName(), df.getLastName(), df.getEmailAddress() },
		  { df.getFirstName(), df.getLastName(), df.getEmailAddress() }	

		};
	};

	@AfterTest()
	private void closeBrowser() {
		Close();

	}

}