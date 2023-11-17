/**  
* @author Palash Soni
* https://www.linkedin.com/in/Palash9088
* https://github.com/Palash9088
* 
*/
package testscripts;//import java.util.*;

import base.PredefinedActions;
import constants.ConstantPaths;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReading;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {

    //private DashboardPage dashboardPage;
    private static PropertyReading prop;

    @BeforeClass
    public void beforeClass() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
        System.setProperty("current.date.time", sdf.format(new Date()));
        PropertyConfigurator.configure(ConstantPaths.LOG4J_PATH);
        prop = new PropertyReading(ConstantPaths.CONFIG_PATH);

    }

    @BeforeMethod
    public void openBrowser() {
        PredefinedActions.initializeBrowser(prop.getValue("url"),
                prop.getValue("browser"),
                Boolean.parseBoolean(prop.getValue("headless")));
    }


    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
            PredefinedActions.takeScreenshot(result.getName());
        PredefinedActions.closeBrowser();
    }
}
