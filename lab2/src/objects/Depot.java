package objects;

public class Depot {
    int id;
    public Depot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "id=" + id +
                '}';
    }


}
