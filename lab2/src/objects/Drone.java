package objects;

/**
 * Drone; subclass of Vehicle
 */
public class Drone extends Vehicle{
    int duration = 0;
    public Drone(Depot d, String name) {
        this.name = name;
        this.depot = d;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
