package mob104.fpoly.myapplication.models;

import androidx.annotation.NonNull;

public class NhanvienModel {
    // id khi lấy key từ firebase về
    private String id;
    private String name;
    private String username;
    private String passwd;
    private String birth_date;
    private String phone_number;
    private String group;
    private double luong;
    private String cccd;

    public NhanvienModel(String id, String name, String username, String passwd, String birth_date, String phone_number, String group, double luong, String cccd) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.passwd = passwd;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.group = group;
        this.luong = luong;
        this.cccd = cccd;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", group='" + group + '\'' +
                ", luong=" + luong +
                ", cccd='" + cccd + '\'' +
                '}';
    }
}
