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

def JENIS_TOKO = ['JENIS_TOKO':'Mall']
def LOKASI_TOKO = ['xLOKASI_TOKO':'DKI Jakarta']
def JUMLAH_SCROLL = 3
def KONDISI = ['xKONDISI':'Baru']

WebUI.callTestCase(findTestCase('Test Cases/Tokopedia/TC_000_TestSearch'), null, FailureHandling.STOP_ON_FAILURE)

def pilihanToko = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_JenisToko',JENIS_TOKO))

if (WebUI.verifyElementPresent(pilihanToko, 3, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.click(pilihanToko, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Element Not Found!')
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_SearchResult/div_checkFilterApplied'), 5, FailureHandling.STOP_ON_FAILURE)

def lokasiToko = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_LokasiToko',LOKASI_TOKO))

if (WebUI.verifyElementPresent(lokasiToko, 3, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.click(lokasiToko, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Element Not Found!')
}

for (int i=0;i<JUMLAH_SCROLL;i++) {
	WebUI.scrollFromViewportOffset(0, 0, 0, 200)
	WebUI.delay(1)
}

WebUI.waitForElementPresent(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.setText(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), '1000', FailureHandling.STOP_ON_FAILURE)
WebUI.sendKeys(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), Keys.chord(Keys.ENTER))
WebUI.verifyElementPresent(findTestObject('Object Repository/Tokopedia_Objects/Category_Tokopedia/div_headerSearchInfo'), 5, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.setText(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), '999999999', FailureHandling.STOP_ON_FAILURE)
WebUI.sendKeys(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), Keys.chord(Keys.ENTER))

def ObjKondisi = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_Kondisi',KONDISI))

WebUI.click(ObjKondisi, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

WebUI.closeBrowser()