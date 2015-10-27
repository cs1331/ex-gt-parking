import java.util.SortedSet;
import java.util.TreeSet;

public class Lot {

    private String name;
    private int capacity;
    private SortedSet<Permit> assignedPermits;

    public Lot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.assignedPermits = new TreeSet<Permit>();
    }

    public int getNumAvailableSpots() {
        return capacity - assignedPermits.size();
    }

    public String getName() {
        return name;
    }

    public void addPermit(Permit newPermit)
      throws ItemAlreadyExistsException {

        if (!assignedPermits.add(newPermit)) {
            throw new ItemAlreadyExistsException(newPermit.toString());
        }
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
