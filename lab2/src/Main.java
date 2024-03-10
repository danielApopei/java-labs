import objects.*;
import utilities.Problem;

import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        Problem pb = new Problem();
        pb.addClient(new Client("C1", ClientType.REGULAR, LocalTime.MIN, LocalTime.MAX));
        pb.addClient(new Client("C2", ClientType.REGULAR, LocalTime.MIN, LocalTime.MAX));
        pb.addClient(new Client("C3", ClientType.REGULAR, LocalTime.MIN, LocalTime.MAX));

        boolean x = pb.addClient(new Client("C4", ClientType.REGULAR, LocalTime.MIN, LocalTime.MAX));
        boolean y = pb.addClient(new Client("C4", ClientType.PREMIUM, LocalTime.MIN, LocalTime.MAX));
        assert x;
        assert !y;
        Depot depotA = new Depot("A");
        Depot depotB = new Depot("B");
        pb.addVehicle(new Truck(depotA, "Car 1"));
        pb.addVehicle(new Truck(depotA, "Car 2"));
        x = pb.addVehicle(new Truck(depotB, "Car 3"));
        y = pb.addVehicle(new Truck(depotB, "Car 3"));
        assert x;
        assert !y;
        System.out.println(x);
        System.out.println(y);
        Vehicle[] vl = pb.getVehicles();
        for(Vehicle v: vl) {
            if(v != null)
                System.out.println(v);
            else break;
        }
        pb.generateProblemSample();
        Solution s = pb.solveProblem();
        s.showSolution();
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Running time: " + runningTime);
        System.out.println("Memory Increase: " + memoryIncrease);
    }
}
