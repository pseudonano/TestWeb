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

// Set variable for dynamic object
def JENIS_TOKO = ['JENIS_TOKO':'Mall']
def LOKASI_TOKO = ['xLOKASI_TOKO':'DKI Jakarta']
def JUMLAH_SCROLL = 3
def KONDISI = ['xKONDISI':'Baru']
def DYNAMIC_RATING = ['xRATING':'Rating 4 ke atas']
def PENAWARAN = ['xPENAWARAN':'COD']
def LAINNYA_FILTER = ['xLAINNYA':'Ready Stock']

// Call Search Test Case
WebUI.callTestCase(findTestCase('Test Cases/Tokopedia/TC_000_TestSearch'), null, FailureHandling.STOP_ON_FAILURE)

// initialize dynamic object
def pilihanToko = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_JenisToko',JENIS_TOKO))

if (WebUI.waitForElementPresent(pilihanToko, 2, FailureHandling.STOP_ON_FAILURE)){
	WebUI.click(pilihanToko, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Element Not Found!')
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_SearchResult/div_checkFilterApplied'), 2, FailureHandling.STOP_ON_FAILURE)

// initialize dynamic object
def lokasiToko = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_LokasiToko',LOKASI_TOKO))

if (WebUI.waitForElementPresent(lokasiToko, 2, FailureHandling.STOP_ON_FAILURE)) {
	WebUI.click(lokasiToko, FailureHandling.STOP_ON_FAILURE)
} else {
	println('Element Not Found!')
}

// perform scrolling gesture
for (int i=0;i<JUMLAH_SCROLL;i++) {
	WebUI.scrollFromViewportOffset(0, 0, 0, 200)
}

// set filter for minimum and maximum price
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), 2, FailureHandling.STOP_ON_FAILURE)
WebUI.setText(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), '1000', FailureHandling.STOP_ON_FAILURE)
WebUI.sendKeys(findTestObject('Object Repository/Page_SearchResult/input_HargaMinimum'), Keys.chord(Keys.ENTER))

WebUI.verifyElementPresent(findTestObject('Object Repository/Tokopedia_Objects/Category_Tokopedia/div_headerSearchInfo'), 2, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), 2, FailureHandling.STOP_ON_FAILURE)
WebUI.setText(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), '999999999', FailureHandling.STOP_ON_FAILURE)
WebUI.sendKeys(findTestObject('Object Repository/Page_SearchResult/input_HargaMaksimum'), Keys.chord(Keys.ENTER))

// //div[@data-testid='divSearchFilter']//*
// initialize dynamic object
def ObjKondisi = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_Kondisi',KONDISI))
println(ObjKondisi)

WebUI.click(ObjKondisi)

// initialize dynamic object
def ObjRating = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_Rating',DYNAMIC_RATING))

WebUI.click(ObjRating, FailureHandling.STOP_ON_FAILURE)

def ObjPenawaran = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_Penawaran',PENAWARAN))

WebUI.click(ObjPenawaran, FailureHandling.STOP_ON_FAILURE)

def ObjLainnya = TestWebUtils.makeObject(findTestObject('Object Repository/Page_SearchResult/div_span_Lainnya',LAINNYA_FILTER))

WebUI.click(ObjLainnya, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.closeBrowser()