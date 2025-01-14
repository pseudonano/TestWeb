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

def dataCategory = ['CATEGORY':'rumah-tangga']
def dataChildCategory = ['SELECTED_CHILD_CATEGORY':'rumah-tangga/dekorasi/cover-kipas-angin']

def dynamicCategoryHover = TestWebUtils.makeObject(findTestObject('Object Repository/HomePage_Tokopedia/a_hoverAllCategories',dataCategory))
def dynamicChildCategory = TestWebUtils.makeObject(findTestObject('Object Repository/HomePage_Tokopedia/a_selectChildCategories',dataChildCategory))

WebUI.openBrowser(BASE_URL, FailureHandling.STOP_ON_FAILURE)

WebUI.mouseOver(findTestObject('Object Repository/HomePage_Tokopedia/div_headerKategori'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage_Tokopedia/div_checkHoverCategories'), 5, FailureHandling.STOP_ON_FAILURE)

if (WebUI.verifyElementPresent(dynamicCategoryHover, 5, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.mouseOver(dynamicCategoryHover, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Element not found!')
}

if (WebUI.verifyElementPresent(dynamicChildCategory, 5, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.click(dynamicChildCategory, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Child element not found!')
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Category_Tokopedia/div_successPickCategory'), 5, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()