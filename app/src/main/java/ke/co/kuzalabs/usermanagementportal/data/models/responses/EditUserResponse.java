package ke.co.kuzalabs.usermanagementportal.data.models.responses;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 22:01
 */
public class EditUserResponse {

    /**
     * name : morpheus
     * job : zion resident
     * updatedAt : 2018-10-26T18:59:06.999Z
     */

    private String name;
    private String job;
    private String updatedAt;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
