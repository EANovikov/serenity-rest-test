package objects;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Person implements Entity {

    private long id;
    private String name;
    private String personJson;

    public static final String PERSON_URL = BASE_URL + "/person";
    public static final String PERSONS_URL = BASE_URL + "/persons";

    public Person() {
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
        personAsJson(id, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPersonJson() {
        return personJson;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void personAsJson(long id, String name){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .build();
        personJson = jsonObject.toString();
    }

    public static List getPersonNames(List<Person> persons){
        List <String> result = new ArrayList<>();
        for(Person person : persons){
            result.add(person.getName());
        }
        return result;
    }
}
