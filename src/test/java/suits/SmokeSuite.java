package suits;

import addUserTests.AddNewUserTest;
import loginTest.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import searchTests.SearchForSystemUserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        AddNewUserTest.class,
        SearchForSystemUserTest.class
}) public class SmokeSuite {
}
