package objects;

/**
 * class that represents an Attraction
 */
public abstract class Attraction implements Comparable<Attraction> {
    public String name;
    @Override
    public int compareTo(Attraction other) {
        return this.name.compareTo(other.name);
    }
}
