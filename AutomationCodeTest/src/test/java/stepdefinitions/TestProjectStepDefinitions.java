package stepdefinitions;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.TestProjectActions;

public class TestProjectStepDefinitions {

    TestProjectActions addTodo = new TestProjectActions();

    @Given("Login using your github account")
    public void login_using_your_github_account() throws IOException {
        addTodo.launchApplication();
        addTodo.enterCredentials();
    }

    @Given("create {int} to do list with random strings")
    public void create_to_do_list_with_random_strings(Integer List){
        addTodo.itemsAdded(List);
    }

    @Then("Logout upon completion") @Then("upon completion log out")
    public void upon_completion_log_out() {
        addTodo.logOut();
    }

    @Then("Login again with the same account")
    public void login_again_with_the_same_account() {
        addTodo.loginAgain();
    }

    @When("Delete your list from {int} - {int}")
    public void delete_your_list_from(Integer start,Integer end){
        addTodo.itemsDeleted(start,end);
    }

    @Then("Close the Browser")
    public void closeTheBrowser() {
        addTodo.quit();
    }
}
