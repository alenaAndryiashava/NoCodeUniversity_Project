package ui;

import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[][] excelCorrectDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/credentials.xlsx", "correctData");
        return ext.parseData();
    }

    @DataProvider
    public static Object[][] excelWrongDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/credentials.xlsx", "wrongCred");
        return ext.parseData();
    }
}
