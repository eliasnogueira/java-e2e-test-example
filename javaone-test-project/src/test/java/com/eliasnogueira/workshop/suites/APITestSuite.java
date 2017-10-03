package com.eliasnogueira.workshop.suites;

import com.eliasnogueira.workshop.rest.FunctionalAPITest;
import com.eliasnogueira.workshop.rest.FunctionalMockAPITest;
import com.eliasnogueira.workshop.rest.SmokeAPITest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite that runs all tests over api package
 * 
 * @author Elias Nogueira | @eliasnogueira
 * @author Edson Yanaga | @yanaga
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            SmokeAPITest.class,
            FunctionalMockAPITest.class,
            FunctionalAPITest.class
        }
)
public class APITestSuite {
 
}
