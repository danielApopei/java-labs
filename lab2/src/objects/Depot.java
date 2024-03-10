package objects;

import java.util.Arrays;
import java.util.Objects;

/**
 * Depot; class that can store vehicles.
 * All vehicles inside depot must have distinct names.
 */
public class Depot {
    private String name;
    private Vehicle[] vehicles;
    int vehicleCount;
    public Depot(String name) {
        this.name = name;
        this.vehicles = new Vehicle[100];
        vehicleCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle ... vehicles) {
        this.vehicles = vehicles;
        for(Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    /**
     * adds a vehicle to the depot
     * @param newVehicle vehicle to be added
     * @return whether the addition was possible (names must be distinct)
     */
    public boolean addVehicle(Vehicle newVehicle) {
        if (vehicleCount >= 100) {
            return false;
        }

        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && vehicle.getName().equals(newVehicle.getName())) {
                return false;
            }
        }

        vehicles[vehicleCount++] = newVehicle;
        return true;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + vehicles.length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(this.name, depot.name);
    }
}
