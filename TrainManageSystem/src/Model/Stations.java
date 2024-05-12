package Model;

public class Stations {
    private String stationId;
    private String stationName;
    private String city;

    public Stations() {
    }

    public Stations(String stationId, String stationName, String city) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.city = city;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
