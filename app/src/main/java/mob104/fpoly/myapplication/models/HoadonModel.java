package mob104.fpoly.myapplication.models;

public class HoadonModel {

    private String Day;
    private String Table;
    private String TotalTime;
    private String TotalMoney;
    private String Staff;

    public HoadonModel(String day, String table, String totalTime, String totalMoney, String staff) {
        Day = day;
        Table = table;
        TotalTime = totalTime;
        TotalMoney = totalMoney;
        Staff = staff;
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
