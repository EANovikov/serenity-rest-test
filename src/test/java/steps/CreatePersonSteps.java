package steps;

import base.BasePersonSteps;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import objects.Person;
import objects.PersonInvalid;
import org.junit.Assert;
import utilites.BodyParser;
import utilites.DatabaseManager;
import utilites.PersonBody;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CreatePersonSteps extends BasePersonSteps {

    private Response response;
    private List<Person> persons = new ArrayList<Person>();
    private List<PersonInvalid> invalidPersons = new ArrayList<PersonInvalid>();
    private BodyParser parser = new BodyParser();
    private DatabaseManager dbManager = new DatabaseManager();

    @Step("Get all person response contains expected multiply person")
    void getResponseContainsExpectedMultiplyPerson() {
        responseGet = SerenityRest.given()
                .contentType("application/json").get(Person.PERSONS_URL);
        responseGet.then().statusCode(200);
        assertThat(parser.jsonBodyToListOfStrings(responseGet.getBody().asString()),
                equalTo(parser.jsonBodyToListOfStrings(response.getBody().asString())));
    }

    @Step("Get person response contains expected person")
     void getResponseContainsExpectedPerson() {
        responseGet = SerenityRest.given()
                .contentType("application/json").get(Person.PERSON_URL + "/1");
        responseGet.then().statusCode(200);
        responseGet.then().body(is(response.getBody().asString()));
    }

    @Step("Create person(s) {0}")
    void createSingleOrMultiplyPerson(List<Person> persons) {
        for (Person person : persons) {
            this.persons.add(person);
        }
        if (persons.size() == 1) {
            response = SerenityRest.given().header("Content-Type", "application/x-www-form-urlencoded").
                    formParam("id", persons.get(0).getId()).
                    formParam("name", persons.get(0).getName()).log().all().
                    when().
                    post(Person.PERSON_URL);
        } else {
            response = SerenityRest.given()
                    .contentType("application/json").
                            body(new PersonBody(persons).getBody()).log().all().
                            when().
                            post(Person.PERSONS_URL);
        }

    }

    @Step("Verify that the person(s) is created successfully")
    void verifySuccessfulCreation() {
        response.then().statusCode(200);
    }

    @Step("Verify that the person(s) have correct name(s)")
    void verifyPersonDataInResponse() {
        if (persons.size() == 1) {
            response.then().body("id", equalTo(((int) persons.get(0).getId())));
            response.then().body("name", equalTo(persons.get(0).getName()));

        } else {
            for (Person person : persons) {
                response.then().body("id", hasItem(((int) person.getId())));
                response.then().body("name", hasItem(person.getName()));
            }
        }
    }

    @Step("Create person(s) {0}, where id type is string instead of number")
    void unsuccessfulCreationPersonAttempt(List<PersonInvalid> persons) throws Exception {
        for (PersonInvalid person : persons) {
            this.invalidPersons.add(person);
        }
        if (persons.size() == 1) {
            response = SerenityRest.given().header("Content-Type", "application/x-www-form-urlencoded")
                    .formParam("id", persons.get(0).getId())
                    .formParam("name", persons.get(0).getName())
                    .request().post(Person.PERSON_URL);
        } else {
            response = SerenityRest.given()
                    .contentType("application/json").
                            body(new PersonBody().setBodyInvalid(persons).getBody()).
                            when().
                            post(Person.PERSONS_URL);
        }
    }

    @Step("Verify that bag request status is recieved")
    void verifyUnsuccessfulCreation() {
        response.then().statusCode(400);
    }

    @Step("Verify that error message points to incorrect id field")
    void verifyUnsuccessfulCreationResponse() {
        response.then().body("status", equalTo("BAD_REQUEST"));
        if (invalidPersons.size() <= 1) {
            response.then().body("message", containsString("Failed to convert property value of type 'java.lang.String' to required type 'long' for property 'id'"));
            //nested exception is java.lang.NumberFormatException: For input string: \"qwerty\""));
            //"Failed to convert property value of type 'java.lang.String' to required type 'long' for property 'id'"));
        } else {
            response.then().body("message", containsString("Cannot deserialize value of type `long` from String \"qwerty\": not a valid Long value"));
        }
    }

    @Step("Check if person(s) are saved in database")
    void checkPersonsInDatabase()throws Exception{
        assertThatPersonListsAreEqual(dbManager.getPersonList(), persons);
    }

}
