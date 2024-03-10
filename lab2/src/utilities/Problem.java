package utilities;

import objects.Client;
import objects.Depot;
import objects.Solution;
import objects.Vehicle;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a VRP problem
 * with Depots, Vehicles and Clients
 * can generate a greedy solution for a given set of Vehicles and Clients
 */
public class Problem {
    private Depot[] depots;
    int depotCount = 0;
    private Client[] clients;
    int clientCount = 0;
    public Problem() {
        depots = new Depot[100];
        clients = new Client[100];
    }

    /**
     * returns an array of all vehicles from all depots
     * @return an array of all vehicles from all depots
     */
    public Vehicle[] getVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();

        for (int i = 0; i < depots.length; i++) {
            if (depots[i] != null) {
                for (Vehicle v : depots[i].getVehicles()) {
                    allVehicles.add(v);
                }
            }
        }

        Vehicle[] vehiclesArray = new Vehicle[allVehicles.size()];
        return allVehicles.toArray(vehiclesArray);
    }

    /**
     * adds a client to the VRP problem
     * @param newClient the client to be added
     * @return whether the addition was possible (names must be distinct)
     */
    public boolean addClient(Client newClient) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getName().equals(newClient.getName())) {
                return false;
            }
        }
        if (clientCount < clients.length) {
            clients[clientCount] = newClient;
            clientCount++;
            return true;
        } else {
            return false; // array full
        }
    }

    /**
     * adds a vehicle to the VRP problem
     * @param newVehicle the vehicle to be added
     * @return whether the addition was possible (names must be distinct)
     */
    public boolean addVehicle(Vehicle newVehicle) {
        for (Depot depot : depots) {
            if (depot != null) {
                for (Vehicle vehicle : depot.getVehicles()) {
                    if (vehicle != null && vehicle.getName().equals(newVehicle.getName())) {
                        return false;
                    }
                }
            }
        }
        String newVehicleDepotName = newVehicle.getName();
        Depot vehicleDepot = null;
        for (Depot depot : depots) {
            if (depot != null && depot.getName().equals(newVehicleDepotName)) {
                vehicleDepot = depot;
                break;
            }
        }

        if (vehicleDepot == null) {
            if (depotCount < depots.length) {
                vehicleDepot = new Depot(newVehicleDepotName);
                depots[depotCount++] = vehicleDepot;
            } else {
                System.out.println("b");
                return false;
            }
        }

        return vehicleDepot.addVehicle(newVehicle);
    }

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot[] depots) {
        this.depots = depots;
    }

    public int getDepotCount() {
        return depotCount;
    }

    public void setDepotCount(int depotCount) {
        this.depotCount = depotCount;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }
    int[][] pathCost;
    int nodeNumber;
    boolean problemGenerated = false;

    /**
     * generates a VRP problem based on the clients and vehicles added
     */
    public void generateProblemSample() {
        nodeNumber = depotCount + clientCount;
        pathCost = new int[nodeNumber][nodeNumber];
        for(int i = 0;i<nodeNumber;i++){
            for(int j = 0;j<nodeNumber;j++) {
                pathCost[i][j] = (int) (Math.random() * 10 + 1);
            }
        }
        problemGenerated = true;
    }

    /**
     * solves the VRP problem generated
     * @return Solution
     */
    public Solution solveProblem() {

        if(!problemGenerated)
            return new Solution();
        ArrayList<Tour> tours = new ArrayList<>();
        for(int i=0;i<depotCount;i++)
        {
            for(Vehicle v : depots[i].getVehicles()) {
                if(v!=null)
                    tours.add(new Tour(v));
            }
        }
        for(int i=0;i<clientCount;i++)
        {
            int clientLocation = i + depotCount;
            String closestCarName = "";
            int shortestDistance = 100;
            for(Tour t: tours){
                int distance = pathCost[t.currentLocation][clientLocation];
                if(distance < shortestDistance) {
                    shortestDistance = distance;
                    closestCarName = t.vehicle.getName();
                }
            }
            for(Tour t: tours){
                int distance = pathCost[t.currentLocation][clientLocation];
                if(distance == shortestDistance) {
                    t.addDestination(clientLocation, clients[clientLocation-depotCount].getName());
                    break;
                }
            }
        }
        return new Solution(tours);
    }
}
