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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser(BASE_URL)

WebUI.delay(5)

// Define the dynamic attribute value  
//def definedDataTestId = "icnHomeDynamicIcon#1" 
Map<String, String> dataHomepageCategory = ['DATA_TEST_ID': 'icnHomeDynamicIcon#1']
Map<String,String> dataCategoryPicker = ['CATEGORY_ID':'A','DATA_TESTID':'llbcBaterai&ChargerKamera']


// Find the test object using the dynamic attribute  
//def dynamicButton = findTestObject("Object Repository/dynamic/dynamic_homepageCategory", ['DATA_TEST_ID': definedDataTestId])  
TestObject dynamicButton = TestWebUtils.makeObject(findTestObject('Object Repository/dynamic/dynamic_homepageCategory', dataHomepageCategory))
TestObject categoryPicker = TestWebUtils.makeObject(findTestObject('Object Repository/dynamic/a_SelectCategory',dataCategoryPicker))
  
//WebUI.waitForElementPresent(dynamicButton, 10)

// Perform actions on the dynamic button  
if (WebUI.verifyElementPresent(dynamicButton, 10,FailureHandling.STOP_ON_FAILURE)) {  
    WebUI.click(dynamicButton,FailureHandling.STOP_ON_FAILURE)  
} else {  
    println("Dynamic button not found!")  
}  

WebUI.delay(5)

WebUI.verifyElementText(findTestObject("Object Repository/Category_Tokopedia/div_category_checker"), "Kategori", FailureHandling.STOP_ON_FAILURE)

if (WebUI.verifyElementPresent(categoryPicker, 5, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.click(categoryPicker,FailureHandling.STOP_ON_FAILURE)
} else {
	println("Dynamic button not found!")
}

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Category_Tokopedia/div_successPickCategory'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

