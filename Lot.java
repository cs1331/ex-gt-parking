import java.util.Set;
import java.util.HashSet;

public class Lot {

    private String name;
    private int capacity;
    private Set assignedPermits;
    // TODO: generics

    public Lot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.assignedPermits = new HashSet();
    }

    public int getNumAvailableSpots() {
        return capacity - assignedPermits.size();
    }

    // TODO: toString, equals, hashCode...
}
