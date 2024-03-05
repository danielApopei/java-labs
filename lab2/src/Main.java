import objects.*;

public class Main {
    public static void main(String[] args) {
        Depot d = new Depot(3);
        Vehicle v = new Vehicle(d, 2);
        Client c = new Client("Eu", ClientType.PREMIUM, new TimeInterval(8,16));
        System.out.println(d);
        System.out.println(v);
        System.out.println(c);
    }
}
