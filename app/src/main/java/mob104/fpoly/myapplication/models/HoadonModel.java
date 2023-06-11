package mob104.fpoly.myapplication.models;

public class HoadonModel {

    private String Day;
    private String Giatien;
    private String Time;
    private String Table;
    private String TotalTime;

    private String TotalMoney;
    private String Staff;

    public HoadonModel() {
    }

    public HoadonModel(String day, String giatien, String time, String table, String totalTime, String totalMoney, String staff) {
        Day = day;
        Giatien = giatien;
        Time = time;
        Table = table;
        TotalTime = totalTime;
        TotalMoney = totalMoney;
        Staff = staff;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getGiatien() {
        return Giatien;
    }

    public void setGiatien(String giatien) {
        Giatien = giatien;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public String getTotalTime() {
        return TotalTime;
    }

    public void setTotalTime(String totalTime) {
        TotalTime = totalTime;
    }

    public String getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }
}
