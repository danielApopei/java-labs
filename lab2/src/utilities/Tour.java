package utilities;

import objects.Vehicle;

import java.util.ArrayList;

/**
 * class that stores a tour, including the current location, current time and the visited destinations in order
 */
public class Tour {
    Vehicle vehicle;
    int currentLocation = 0;
    int currentTime = 0;
    ArrayList<Integer> destinations;
    ArrayList<String> clientNames;
    public Tour(Vehicle vehicle) {
        destinations = new ArrayList<>();
        clientNames = new ArrayList<>();
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Integer> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Integer> destinations) {
        this.destinations = destinations;
    }

    /**
     * adds a destination; also moves the car of the tour to that location
     * @param location new location
     * @param name new name
     */
    public void addDestination(int location, String name, int timeToMove) {
        destinations.add(location);
        currentLocation = location;
        clientNames.add(name);
        currentTime += timeToMove;
        currentTime = currentLocation % 24;
    }

    @Override
    public String toString() {
        return "Tour: vehicleName = " + vehicle.getName() + "\nroute: "+destinations + "\nclients: "+clientNames;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public ArrayList<String> getClientNames() {
        return clientNames;
    }

    public void setClientNames(ArrayList<String> clientNames) {
        this.clientNames = clientNames;
    }
}
