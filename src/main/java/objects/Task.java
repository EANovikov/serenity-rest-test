package objects;

public class Task implements Entity {

    /**
     * JSON body example
     * {
     * "id": "1",
     * "title": "Execute regression",
     * "remindDate": "2017-11-22",
     * "comment" : "use automated tests"
     * }
     */

    private long id;
    private String title;
    private String remindDate;
    private String comment;

    public static final String TASK_URL = BASE_URL + "/task";
    public static final String TASKS_URL = BASE_URL + "/tasks";

    Task(long id, String title, String remindDate, String comment){
        this.id = id;
        this.title = title;
        this.remindDate = remindDate;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
