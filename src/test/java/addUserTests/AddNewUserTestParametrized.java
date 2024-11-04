package addUserTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class AddNewUserTestParametrized extends BaseTest {

    final  String USER_NAME = "User" + Util.getDateAndTimeFormatted();
    final static String COMMA = ",";

    @Test
    @Parameters({
            "Admin"+COMMA+"Enabled"
            ,"ESS"+COMMA+"Disabled"
    })
    @TestCaseName("createdUsers: userRole ={0}, useStatus ={1}")
    public void addNewUser(String userRole, String status ) {
        dashboardPage.openDashboardPage()
                .getSideBarElements().clickOnMenuItemAdmin()
                .clickOnButtonAdd()
            .checkRedirectToSaveSystemUserPage()
                .selectOptionFromUserRoleDropdown(userRole)
                .enterTextIntoEmployeeName("Jobin Mathew Sam")
                .selectOptionFromStatusDropdown(status)
                .enterTextIntoUserName(USER_NAME)
                .enterTestIntoPassword("StrongPass2024!")
                .enterTestIntoConfirmPassword("StrongPass2024!")
                .clickOnButtonSave()
            .checkSuccessToastMessage("Successfully Saved")
            .checkRedirectToViewSystemUserPage()
            .checkUserWasCreated(USER_NAME)
        ;

    }

   /* @After
    public void deleteUser(){
        dashboardPage
                .openDashboardPage()
                .getSideBarElements().clickOnMenuItemAdmin()
             .checkRedirectToViewSystemUserPage()
                .deleteUserWithNameTillPresent(USER_NAME);
    }*/

}
