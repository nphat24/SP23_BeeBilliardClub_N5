package mob104.fpoly.myapplication.models;

import androidx.annotation.NonNull;

public class NhanvienModel {
    // id khi lấy key từ firebase về
    private String id;
    private String name;
    private String username;
    private String password;
    private String birth_date;
    private String group;
    private double luong;
    private String cccd;

    public NhanvienModel(String id, String name, String username, String password, String birth_date, String group, double luong, String cccd) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birth_date = birth_date;
        this.group = group;
        this.luong = luong;
        this.cccd = cccd;
    }

    public NhanvienModel() {
    }

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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    @Override
    public String toString() {
        return "NhanvienModel{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", group='" + group + '\'' +
                ", luong=" + luong +
                ", cccd='" + cccd + '\'' +
                '}';
    }
}
