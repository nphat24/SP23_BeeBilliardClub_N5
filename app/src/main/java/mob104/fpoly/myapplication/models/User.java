package mob104.fpoly.myapplication.models;

public class User {
    private String cccd;
    private String name;
    private String username;
    private String password;
    private String phone_number;
    private String group;


    public User() {
    }

    public User(String name, String username, String password, String phone_number, String group, String cccd) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.group = group;
        this.cccd = cccd;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
}
