package utilites;

import objects.Person;
import objects.PersonInvalid;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class PersonBody extends Body {

    public PersonBody(){
    }

    public PersonBody(Person... persons){
        if (persons.length == 1) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", persons[0].getId())
                    .add("name", persons[0].getName())
                    .build();
            bodyStr = jsonObject.toString();
        } else if(persons.length > 1){
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for(Person person : persons) {
                jsonArray.add(Json.createObjectBuilder()
                        .add("id", person.getId())
                        .add("name", person.getName()));
            }
            bodyStr = jsonArray.build().toString();
        }
        else {
            bodyStr = "";
        }
    }

    public PersonBody(List<Person> persons){
        if (persons.size() == 1) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", persons.get(0).getId())
                    .add("name", persons.get(0).getName())
                    .build();
            bodyStr = jsonObject.toString();
        } else if(persons.size() > 1){
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for(Person person : persons) {
                jsonArray.add(Json.createObjectBuilder()
                        .add("id", person.getId())
                        .add("name", person.getName()));
            }
            bodyStr = jsonArray.build().toString();
        }
        else {
            bodyStr = "";
        }
    }

    public PersonBody setBodyInvalid(List <PersonInvalid> persons) {
        if (persons.size() == 1) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", persons.get(0).getId())
                    .add("name", persons.get(0).getName())
                    .build();
            bodyStr = jsonObject.toString();
        } else if(persons.size() > 1){
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for(PersonInvalid person : persons) {
                jsonArray.add(Json.createObjectBuilder()
                        .add("id", person.getId())
                        .add("name", person.getName()));
            }
            bodyStr = jsonArray.build().toString();
        }
        else {
            bodyStr = "";
        }
      return this;
    }

    public PersonBody setBody(List <Person> persons) {
        if (persons.size() == 1) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", persons.get(0).getId())
                    .add("name", persons.get(0).getName())
                    .build();
            bodyStr = jsonObject.toString();
        } else if(persons.size() > 1){
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for(Person person : persons) {
                jsonArray.add(Json.createObjectBuilder()
                        .add("id", person.getId())
                        .add("name", person.getName()));
            }
            bodyStr = jsonArray.build().toString();
        }
        else {
            bodyStr = "";
        }
     return this;
    }

    public PersonBody add(Person person){
        persons.add(person);
        return this;
    }

    public void build(){
        setBody(persons);
    }

    public String buildAndGetBody(){
        setBody(persons);
        return getBody();
    }

}
