package mob104.fpoly.myapplication.models;

public class BanchoiModel {
    private String Name;
    private String Start;
    private String End;
    private String Price;

    public BanchoiModel() {
    }

    public BanchoiModel(String name, String start, String end, String price) {
        Name = name;
        Start = start;
        End = end;
        Price = price;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
