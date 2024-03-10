package objects;

/**
 * abstract class representing a Vehicle
 */
public abstract class Vehicle {
    protected Depot depot;
    protected String name;

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "depot=" + depot +
                ", name=" + name +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vehicle other)) {
            return false;
        }
        return name.equals(other.name);
    }
}
