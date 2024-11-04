package suits;

import addUserTests.AddNewUserTest;
import categories.SmokeTestFilter;
import loginTest.LoginTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory({SmokeTestFilter.class})
@Suite.SuiteClasses({
        LoginTest.class,
        AddNewUserTest.class

})
public class SmokeSuiteWithCategories {


}
