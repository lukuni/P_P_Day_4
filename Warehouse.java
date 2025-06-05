import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    String name;
    List<Location> locations;

    public Warehouse(String name) {
        this.name = name;
        this.locations = new ArrayList<>();
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String toString() {
        return name;
    }
}
