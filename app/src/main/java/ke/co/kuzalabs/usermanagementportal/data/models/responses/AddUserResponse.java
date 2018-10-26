package ke.co.kuzalabs.usermanagementportal.data.models.responses;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 19:48
 */
public class AddUserResponse {

    /**
     * name : morpheus
     * job : leader
     * id : 481
     * createdAt : 2018-10-26T16:49:34.142Z
     */

    private String name;
    private String job;
    private String id;
    private String createdAt;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
