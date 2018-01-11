package page_objects;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.DataReader;

import java.io.IOException;

import static org.openqa.selenium.support.How.ID;
import static org.openqa.selenium.support.How.XPATH;

public class ByInputFromExls extends CommonAPI {
    @FindBy(how = XPATH, using = ".//*[@id='fm-login-id']")
    public static WebElement account;
    @FindBy(how = ID, using = "fm-login-password")
    public static WebElement password;
    @FindBy(className = "notice-descript")
    public static WebElement signInErrorMesage;

    DataReader dtr = new DataReader();
    //T3ALI_BE _TC01
    public String[] getDataCol2(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 2); //col 2 = email
        return output;
    }
    //T3ALI_BE _TC01
    public String[] getDataCol3(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 3); //col 3 = password
        return output;
    }
    //T3ALI_BE _TC01
    public String[] getAssertData(String fileName) throws IOException {
        String path = "../AliBaba/data/" + fileName;
        String[] output = dtr.colReader(path, 4);
        return output;
    }

    //T3ALI_BE _TC01 LogIn by using excel sheet data
    public String[] getVerificationValue(String fileName) throws IOException, InterruptedException {
        String[] col2Value = getDataCol2(fileName);
        String[] col3Value = getDataCol3(fileName);
        String[] actual = new String[col2Value.length];
        for (int i = 0; i < col2Value.length; i++) {
            sleepFor(1);
            inputValueInTextBoxByWebElement(account, col2Value[i]);
            inputValueInTextBoxByWebElement(password, col3Value[i]);
            sleepFor(1);
            // actual[i] = getCurrentPageTitle();
            actual[i] = getTextByWebElement(signInErrorMesage);
            clearInputBox(account);
            clearInputBox(password);
            sleepFor(1);
        }
        return actual;
    }
}
    /*// AMZ_TS4_TC2:  Search multiple items by bar-code from a xls file
    public String[] searchItemByBarCode(String fileName) throws IOException, InterruptedException {
        String[] items = getData(fileName);
        String[] assertItems = getAssretData(fileName);
        String[] actual = new String[items.length];
        for (int i=0; i<items.length; i++){
            sleepFor(1);
            typeByIdNEnter("twotabsearchtextbox", items[i]);
            sleepFor(1);
            actual[i] = getTextByXpath("//*[text()='"+assertItems[i]+"']");
            clearInputFieldById("twotabsearchtextbox");
            sleepFor(1);
        }
        return actual;
   } }*/

/*public class ByInputFromExls extends CommonAPI {
    DataReader dtr = new DataReader();
    public String[] getData(String fileName) throws IOException {
        String path = "../Amazon/data/" + fileName;
        String [] output = dtr.colReader(path,2);
        return output;
    }
    public String[] getAssretData(String fileName) throws IOException {
        String path = "../Amazon/data/" + fileName;
        String [] output = dtr.colReader(path,3);
        return output;
    }
    // AMZ_TS4: Use test data to search multiple items
    // AMZ_TS4_TC1:  Search multiple items by name from a xls file
    public String[] searchItemByName(String fileName) throws IOException, InterruptedException {
        String[] items = getData(fileName);
        String[] actual = new String[items.length];
        for (int i=0; i<items.length; i++){
            sleepFor(1);
            typeByIdNEnter("twotabsearchtextbox", items[i]);
            sleepFor(1);
            actual[i] = getCurrentPageTitle();
            clearInputFieldById("twotabsearchtextbox");
            sleepFor(1);
        }
        return actual;
    }
    // AMZ_TS4_TC2:  Search multiple items by bar-code from a xls file
    public String[] searchItemByBarCode(String fileName) throws IOException, InterruptedException {
        String[] items = getData(fileName);
        String[] assertItems = getAssretData(fileName);
        String[] actual = new String[items.length];
        for (int i=0; i<items.length; i++){
            sleepFor(1);
            typeByIdNEnter("twotabsearchtextbox", items[i]);
            sleepFor(1);
            actual[i] = getTextByXpath("//*[text()='"+assertItems[i]+"']");
            clearInputFieldById("twotabsearchtextbox");
            sleepFor(1);
        }
        return actual;
   } }
*/