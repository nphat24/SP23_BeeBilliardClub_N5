package mob104.fpoly.myapplication.models;

public class BanchoiModel {
    private String Name;
    private String Start;
    private String End;

    public BanchoiModel() {
    }

    public BanchoiModel(String name, String start, String end) {
        Name = name;
        Start = start;
        End = end;
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
}
