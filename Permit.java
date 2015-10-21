public class Permit implements Comparable<Permit> {

    private String name;
    private int gtID;

    public Permit(String name, int gtID) {
        this.name = name;
        this.gtID = gtID;
    }

    @Override
    public int compareTo(Permit o) {
        return this.gtID - o.gtID;
    }

    @Override
    public boolean equals(Object o) {
        Permit other = (Permit) o;
        return this.gtID == other.gtID;
    }

    @Override
    public int hashCode() {
        return gtID;
    }

    @Override
    public String toString() {
        return name + " (" + String.valueOf(gtID) + ")";
    }
}
