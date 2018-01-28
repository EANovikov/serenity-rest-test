package utilites;

import objects.Person;

import java.util.List;

public abstract class Body {

    String bodyStr = "";
    List<Person> persons;

    public String getBody() {
        return bodyStr;
    }

    public void printBody() {
        System.out.println(bodyStr);
    }

}
