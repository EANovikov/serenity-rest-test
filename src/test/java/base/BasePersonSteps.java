package base;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import objects.Person;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public abstract class BasePersonSteps {

    protected Response responseGet;

    @Step("Get all person is succesfull")
    public void getAllPersonIsSuccessfull() {
        responseGet = SerenityRest.given()
                //Alternative way of authentication is: .auth().basic("admin", "admin")
                .header("Authorization", Person.AUTH)
                .contentType("application/json").get(Person.PERSONS_URL);
        responseGet.then().statusCode(200);
    }

    public static void assertThatPersonListsAreEqual(List<Person> expected, List<Person> actual) {
        for (int i = 0; (i < actual.size()) && (i < expected.size()); i++) {
            assertThat(actual.get(i).getId(), equalTo(expected.get(i).getId()));
            assertThat(actual.get(i).getName(), equalTo(expected.get(i).getName()));
        }
    }

}
