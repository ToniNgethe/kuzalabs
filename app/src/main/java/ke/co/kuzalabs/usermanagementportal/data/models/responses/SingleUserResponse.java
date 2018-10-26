package ke.co.kuzalabs.usermanagementportal.data.models.responses;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 22:27
 */
public class SingleUserResponse {

    /**
     * data : {"id":2,"first_name":"Janet","last_name":"Weaver","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"}
     */
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * first_name : Janet
         * last_name : Weaver
         * avatar : https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg
         */

        private int id;
        private String first_name;
        private String last_name;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
