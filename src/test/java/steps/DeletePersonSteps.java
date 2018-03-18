package steps;

import base.BasePersonSteps;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import objects.Person;
import static org.hamcrest.Matchers.not;

public class DeletePersonSteps extends BasePersonSteps {

    private int id;

    @Step("Delete person by id {0}")
    public void deletePersonById(int id){
        this.id= id;
        responseGet = SerenityRest.given()
                .header("Authorization", Person.AUTH)
                .contentType("application/json").delete(Person.PERSON_URL + "/" + 1);
        responseGet.then().statusCode(200);
    }

    @Step("Get all person to check that person removed")
    public void getResponseWithDoesNotContainRemovedPerson() {
        responseGet = SerenityRest.given()
                .header("Authorization", Person.AUTH)
                .contentType("application/json").get(Person.PERSONS_URL);
        responseGet.then().statusCode(200);
        responseGet.then().body("id", not(id));
    }

}
