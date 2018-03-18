package utilites;
import objects.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = ConfigReader.getProperty("dbDriver");
    private static final String DB_URL = ConfigReader.getProperty("dbUrl");
    //  Database credentials
    private static final String USER = ConfigReader.getProperty("dbUser");
    private static final String PASS = ConfigReader.getProperty("dbPass");
    private Connection connection = null;
    private Statement stmt = null;

    public DatabaseManager(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }catch (SQLException e) {
            System.err.println("Database connection issue!");
            e.printStackTrace();
        }
    }

    public List<Person> getPersonList() throws Exception {
        String query = "SELECT id, name FROM person ORDER BY id";
        List <Person> dbPeople = new ArrayList<>();
        //Class.forName("org.postgresql.Driver");
        try {
            //connection = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long id = rs.getInt("id");
                String name = rs.getString("name");
                dbPeople.add(new Person(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return dbPeople;
    }

}
