package objects;

import java.time.LocalTime;
import java.util.Objects;

/**
 * class representing a Client with a name, type and visiting hours
 */
public class Client {
    private String name;
    private ClientType type;
    private LocalTime minTime;
    private LocalTime maxTime;

    public Client() {
        this.name = "";
    }

    public Client(String name) {
        this.name = name;
    }
    public Client(String name, ClientType type, LocalTime minTime, LocalTime maxTime) {
        this(name);
        this.type = type;
        this.minTime = minTime;
        this.maxTime = maxTime;
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

    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }
}
