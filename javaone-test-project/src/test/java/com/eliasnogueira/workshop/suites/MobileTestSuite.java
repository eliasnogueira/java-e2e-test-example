package com.eliasnogueira.workshop.suites;

import com.eliasnogueira.workshop.mobile.AddPerson_Successfully_Test;
import com.eliasnogueira.workshop.mobile.AlterPerson_RemoveAllInformation_Test;
import com.eliasnogueira.workshop.mobile.AlterPerson_Sucessfully_Test;
import com.eliasnogueira.workshop.mobile.RemovePersonTest;
import com.eliasnogueira.workshop.web.AddPerson_NoFillAllInputs_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite that runs all tests over mobile package
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            // acceptance tests
            AddPerson_Successfully_Test.class,
            AlterPerson_Sucessfully_Test.class,
            RemovePersonTest.class,
            
            // functional tests
            AddPerson_NoFillAllInputs_Test.class,
            AlterPerson_RemoveAllInformation_Test.class
        }
)
public class MobileTestSuite {
    
}


