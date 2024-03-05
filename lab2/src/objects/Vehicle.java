package objects;

public class Vehicle {
    Depot depot;
    int id;
    public Vehicle(Depot depot, int id) {
        this.depot = depot;
        this.id = id;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "depot=" + depot +
                ", id=" + id +
                '}';
    }
}
