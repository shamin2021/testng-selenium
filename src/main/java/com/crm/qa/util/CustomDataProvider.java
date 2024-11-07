package com.crm.qa.util;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    @DataProvider(name = "productName")
    public Object[][] getProductName() {
        return new Object[][]{
                {"Sleeveless Satin Midi In Print"}
        };
    }
    @DataProvider(name = "productFilter")
    public Object[][] getProductCategory() {
        return new Object[][]{
                {"Clothing","Dress", "XXL" , "lowprice"}
        };
    }
    @DataProvider(name = "productMore")
    public Object[][] getProductMore() {
        return new Object[][]{
                {"Clothing","Dress", "Sleeveless Satin Midi In Print","XS"}
        };
    }

}


