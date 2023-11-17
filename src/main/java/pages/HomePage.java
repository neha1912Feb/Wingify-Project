/**  
* @author Palash Soni
* https://www.linkedin.com/in/Palash9088
* https://github.com/Palash9088
* 
*/
package pages;//import java.util.*;

import base.PredefinedActions;
import constants.ConstantPaths;
import utils.PropertyReading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends PredefinedActions {

    private static HomePage homePage;
    private static PropertyReading homePageProp;

    private HomePage() {
        homePageProp = new PropertyReading(ConstantPaths.PROP_PATH + "HomePageProp.properties");
    }

    public static HomePage getHomePage() {
        if (homePage == null)
            homePage = new HomePage();
        return homePage;
    }

    public void clickOnAmountHead() {
        clickOnElement(homePageProp.getValue("amountTable"), true);
    }

    public List<Double> getAmountList() {
        return getWebElementListInDouble(homePageProp.getValue("amountList"), true);
    }
    public boolean isAmountListSorted()
    {   List <Double> amountList = getAmountList();
        List<Double> tempList = new ArrayList<>(amountList);
        Collections.sort(tempList);
        return amountList.equals(tempList);
    }
}
