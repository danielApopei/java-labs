package objects;

import java.util.Objects;

public class Client {
    String name;
    ClientType type;
    TimeInterval visitingInterval;
    public Client(String name) {
        this.name = name;
        this.type = ClientType.REGULAR;
        this.visitingInterval = new TimeInterval(0, 0);
    }
    public Client(String name, ClientType type, TimeInterval visitingInterval) {
        this(name);
        this.type = type;
        this.visitingInterval = visitingInterval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public TimeInterval getVisitingInterval() {
        return visitingInterval;
    }

    public void setVisitingInterval(TimeInterval visitingInterval) {
        this.visitingInterval = visitingInterval;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", visitingInterval=" + visitingInterval +
                '}';
    }
}
