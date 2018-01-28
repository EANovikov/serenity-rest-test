package steps;

import base.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import objects.Person;
import objects.PersonInvalid;
import org.junit.runner.RunWith;
import steps.CreatePersonSteps;

import java.util.List;

@RunWith(SerenityRunner.class)
public class PersonStepDefinitions extends BaseTest {

    @Steps
    private CreatePersonSteps createSteps;
    @Steps
    private DeletePersonSteps deleteSteps;

    @Given("^I can get all person$")
    public void iWantToCreateAPerson() throws Throwable {
        createSteps.getAllPersonIsSuccessfull();
    }

    @When("^I request creation person with:$")
    public void iRequestCreationPersonWith(List<Person> persons) throws Throwable {
        createSteps.createSingleOrMultiplyPerson(persons);
    }

    @When("^I request creation person with invalid data:$")
    public void iRequestCreationPersonWithInvalidData(List<PersonInvalid> persons) throws Throwable {
        createSteps.unsuccessfulCreationPersonAttempt(persons);
    }

    @When("^I request to remove person with id (\\d+)$")
    public void iRequestToRemovePersonWithId(int id) throws Throwable {
        deleteSteps.deletePersonById(id);
    }

    @Then("^I should get successful result of creation$")
    public void iShouldGetSuccessfulResultWithPersonDataInResponse() throws Throwable {
        createSteps.verifySuccessfulCreation();
        createSteps.verifyPersonDataInResponse();
    }

    @Then("^I make sure, that one person was added$")
    public void iShouldMakeSureThatOnePersonWasAdded() throws Throwable {
        createSteps.checkPersonsInDatabase();
        createSteps.getResponseContainsExpectedPerson();
    }

    @Then("^I make sure, that multiply person were added$")
    public void iShouldMakeSureThatMultiplyPersonWereAdded() throws Throwable {
        createSteps.checkPersonsInDatabase();
        createSteps.getResponseContainsExpectedMultiplyPerson();
    }

    @Then("^I should get creation error$")
    public void iShouldGetResultWithPersonDataInResponse() throws Throwable {
        createSteps.verifyUnsuccessfulCreation();
        createSteps.verifyUnsuccessfulCreationResponse();
    }

    @Then("^I can see that this person deleted from output$")
    public void iCanSeThatPersonDeletedFromOutput() throws Throwable {
        deleteSteps.getResponseWithDoesNotContainRemovedPerson();
    }
}
