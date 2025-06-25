package runner;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"defenitions", "hooks"},
    plugin = {"pretty",                                   // Pretty console output
                "html:target/cucumber-report.html",          // HTML report
                "json:target/cucumber-report.json",          // JSON report
                "junit:target/cucumber-report.xml"},         // JUnit XML report
        
    monochrome = true

)

public class TestRunner {
}