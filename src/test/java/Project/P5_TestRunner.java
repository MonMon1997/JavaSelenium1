package Project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
        features = "src/test/resources/googlesearch.feature",
        glue = "Project"
        )

public class P5_TestRunner {
}
