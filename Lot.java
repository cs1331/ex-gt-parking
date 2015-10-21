import java.util.Set;
import java.util.TreeSet;

public class Lot {

    private String name;
    private int capacity;
    private Set<Permit> assignedPermits;

    public Lot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.assignedPermits = new TreeSet<Permit>();
    }

    public int getNumAvailableSpots() {
        return capacity - assignedPermits.size();
    }

    @Override
    public boolean equals(Object o) {
        Lot other = (Lot) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
