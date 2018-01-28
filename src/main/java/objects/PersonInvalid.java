package objects;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class PersonInvalid {

    private String id;
    private String name;
    private String personJson;

    public PersonInvalid() {
    }

    public PersonInvalid(String id, String name) {
        this.id = id;
        this.name = name;
        personAsJson(id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPersonJson() {
        return personJson;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void personAsJson(String id, String name){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", id.toString())
                .add("name", id.toString())
                .build();
        personJson = jsonObject.toString();
    }

    public static List getPersonNames(List<PersonInvalid> persons){
        List <String> result = new ArrayList<>();
        for(PersonInvalid person : persons){
            result.add(person.getName().toString());
        }
        return result;
    }

}
