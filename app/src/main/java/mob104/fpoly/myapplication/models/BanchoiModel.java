package mob104.fpoly.myapplication.models;

public class BanchoiModel {
    private String Start;
    private String End;

    public BanchoiModel() {
    }

    public BanchoiModel(String start, String end) {
        Start = start;
        End = end;
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
