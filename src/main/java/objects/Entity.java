package objects;

import utilites.ConfigReader;

public interface Entity {

    String BASE_URL = ConfigReader.getProperty("baseUrl");
    String AUTH = ConfigReader.getProperty("authString");

    long getId();

    void setId(long id);
}
