package ke.co.kuzalabs.usermanagementportal.data.models.requests;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 19:48
 */
public class AddUserRequest {

    /**
     * name : morpheus
     * job : leader
     */

    private String name;
    private String job;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
