package mob104.fpoly.myapplication.models;

import androidx.annotation.NonNull;

public class NhanvienModel {
    private String name ;
    private int passwd;
    private int phonenumber;
    private String username;
    private String group;
    private String cccd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
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

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    private String luong;
    private String ngaysinh;

    public NhanvienModel(String name, int passwd, int phonenumber, String username, String group, String cccd, String luong, String ngaysinh) {
        this.name = name;
        this.passwd = passwd;
        this.phonenumber = phonenumber;
        this.username = username;
        this.group = group;
        this.cccd = cccd;
        this.luong = luong;
        this.ngaysinh = ngaysinh;
    }

    public NhanvienModel() {
    }


}
