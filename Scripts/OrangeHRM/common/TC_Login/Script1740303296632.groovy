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

KeywordLogger logger = new KeywordLogger()

def data = findTestData('Data Files/OrangeHRM/LoginData')

if (data == null) {
	println "Error: Data files did not exists"
} else {
	println "Data loaded successfully!"
	for (def index : (1..data.getRowNumbers())) {
		String username = data.getValue('Username', index)
		String password = data.getValue('Password', index)
		String expectedResult = data.getValue("ExpectedResult", index)
		
		WebUI.openBrowser('')
		
		WebUI.navigateToUrl('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login')
		
		WebUI.setText(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/input_Username_username'), username)
		
		WebUI.setEncryptedText(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/input_Password_password'), password)
		
		WebUI.click(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/button_Login'))
		
		if (expectedResult == 'Success') {
			WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/h6_validateDashboard'), 10)
			WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/common/TC_logout'), null, FailureHandling.STOP_ON_FAILURE)
			WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/common/TC_closeBrowser'), null, FailureHandling.STOP_ON_FAILURE)
			continue
		} else if (expectedResult == 'Failed') {
			WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Page_OrangeHRM/p_invalidCredentials'), 10)
			WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/common/TC_closeBrowser'), null, FailureHandling.STOP_ON_FAILURE)
			continue
		} else {
			logger.logError("Unexpected result for login attempt with user: ${username}")
			WebUI.callTestCase(findTestCase('Test Cases/OrangeHRM/common/TC_closeBrowser'), null, FailureHandling.STOP_ON_FAILURE)
			break
		}
	}
}



