package com.eliasnogueira.workshop.suites;

import com.eliasnogueira.workshop.web.AddPerson_NoFillAllInputs_Test;
import com.eliasnogueira.workshop.web.AddPerson_Successfully_Test;
import com.eliasnogueira.workshop.web.AlterPeson_RemoveAllInformation_Test;
import com.eliasnogueira.workshop.web.AlterPeson_Successfully_Test;
import com.eliasnogueira.workshop.web.RemovePerson_Successfully_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite that runs all tests over web package
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
@RunWith(Suite.class)
@SuiteClasses(
        {
            // acceptance (because we successfully created a person, and it' a crud)
            AddPerson_Successfully_Test.class,
            AlterPeson_Successfully_Test.class,
            RemovePerson_Successfully_Test.class,
            
            // funciontal test (testing sad path)
            AddPerson_NoFillAllInputs_Test.class,
            AlterPeson_RemoveAllInformation_Test.class
        }
)
public class WebTestSuite {

}
