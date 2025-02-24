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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory


//WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/CommonTestCase/TC_openAndAccessWeb'), null, FailureHandling.STOP_ON_FAILURE)

String username = username

String password = password

String expectedResult = expectedResult

WebUI.setText(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/input_Username_username'), username)

WebUI.setEncryptedText(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/input_Password_password'), password)

WebUI.click(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/button_Login'))


if (expectedResult == 'Success') {
    WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/h6_validateDashboard'), 10 ) 
	WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/CommonTestCase/TC_logout'), null, FailureHandling.STOP_ON_FAILURE)
//	WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/CommonTestCase/TC_closeBrowser'), null, FailureHandling.STOP_ON_FAILURE)
    
} else if (expectedResult == 'Failed') {
    WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/p_invalidCredentials'), 10)
//	WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/CommonTestCase/TC_closeBrowser'), null, FailureHandling.STOP_ON_FAILURE)
	
}


