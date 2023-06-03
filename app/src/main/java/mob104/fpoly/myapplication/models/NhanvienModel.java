package mob104.fpoly.myapplication.models;

import androidx.annotation.NonNull;

public class NhanvienModel {
    private String name ;
    private String passwd;
    private String phonenumber;
    private String username;
    private String group;

    public NhanvienModel() {
    }

    public NhanvienModel(String name, String passwd, String phonenumber, String username, String group) {
        this.name = name;
        this.passwd = passwd;
        this.phonenumber = phonenumber;
        this.username = username;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @NonNull
    @Override
    public String toString() {
        return "User:" + username + passwd;
    }
}
