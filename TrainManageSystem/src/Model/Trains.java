package Model;

public class Trains {
    private String type;
    private String id;
    private Integer totalseats;

    public Trains() {
    }

    public Trains(String type, String id, Integer totalseats) {
        this.type = type;
        this.id = id;
        this.totalseats = totalseats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotalseats() {
        return totalseats;
    }

    public void setTotalseats(Integer totalseats) {
        this.totalseats = totalseats;
    }
}
