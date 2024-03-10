package objects;

/**
 * Truck; subclass of Vehicle
 */
public class Truck extends Vehicle{
    int capacity;
    public Truck(Depot d, String name) {
        this.name = name;
        this.depot = d;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
