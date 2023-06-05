package ui;

import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public Object[][] excelCorrectDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/main/resources/credentials.xlsx", "correctData");
        return ext.parseData();
    }

    @DataProvider
    public Object[][] excelWrongDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/main/resources/credentials.xlsx", "wrongData");
        return ext.parseData();
    }

}