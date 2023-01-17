package Runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(tags = "", features = {"src/test/resources/features/TestProject_TodoList.feature"}, glue = {"stepdefinitions"}, plugin = {})
    
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    
}