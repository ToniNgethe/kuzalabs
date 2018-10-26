package ke.co.kuzalabs.usermanagementportal.data.models.requests;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 22:53
 */
public class RegisterRequest {

    /**
     * email : sydney@fife
     * password : pistol
     */

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
