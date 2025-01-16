import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement

String textToSearch = 'mob'
String dropdownXPath = "//div[@data-testid='initialStateDropdown']"
String dropdownXPathBarang = "//div[@data-testid='initialStateDropdown']//a//div[@class='css-1882grf']/span"
String dropdownXPathToko = "//div[@data-testid='initialStateDropdown']//a//div//span[1]/span"

WebUI.comment('Given user application homepage')
WebUI.openBrowser('https://www.tokopedia.com/')
WebUI.comment('When user click search bar')
WebUI.click(findTestObject('Object Repository/Tokopedia_Objects/HomePage_Tokopedia/input_homepageSearch'), FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementPresent(findTestObject('Object Repository/Tokopedia_Objects/HomePage_Tokopedia/div_checkSearchDropdown'), 5, FailureHandling.STOP_ON_FAILURE)
WebUI.comment('And user input character')
WebUI.setText(findTestObject('Object Repository/Tokopedia_Objects/HomePage_Tokopedia/input_homepageSearch'), textToSearch, FailureHandling.STOP_ON_FAILURE)
// get all available suggestion
def driver = DriverFactory.getWebDriver()
WebElement parentXpath = driver.findElement(By.xpath(dropdownXPath))  

List<WebElement> itemXpath = parentXpath.findElements(By.xpath(dropdownXPathBarang))
List<WebElement> tokoXpath = parentXpath.findElements(By.xpath(dropdownXPathToko))

for(WebElement item:itemXpath+tokoXpath) {
//	println(item.getText())
	if (item.getText().toLowerCase()=='charger mobil') {
		item.click()
		break
	} else {
		println('Item not in sugested result')
	}
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Tokopedia_Objects/Category_Tokopedia/div_headerSearchInfo'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()