package com.automation.rms.remitron.util;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name="UserLogin")
    public String [][] UserLoginDataProvider()
    {
        String fName = System.getProperty("user.dir") + "/TestData/DDT_DATA.xlsx";
        int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet1");

        // Need Only one row (2nd row), so array size is [1][ttlColCnt]
        String userData[][] = new String[1][ttlColCnt];
        for (int colNo = 0; colNo < ttlColCnt; colNo++) {
            userData[0][colNo] = ReadExcelFile.getSellValue(fName, "Sheet1", 1, colNo);
        }
        return userData;
    }



}
