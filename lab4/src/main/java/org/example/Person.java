package org.example;


import java.util.ArrayList;
import java.util.List;

/**
 * represents a person (either a driver or passenger)
 * if it is a driver, then the list of the destinations represents the order
 */
public class Person implements Comparable<Person> {
    private PersonType type;
    private String name;
    private int age;
    private String startingLocation;
    private List<String> destinations = new ArrayList<>();
    public PersonType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public List<String> getDestinations() {
        return destinations;
    }
    public Person(PersonType type, String name, int age, String startingLocation) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.startingLocation = startingLocation;
    }

    public Person(PersonType type, String name, int age, List<String> destinations, String startingLocation) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.destinations = destinations;
        this.startingLocation = startingLocation;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.getName());
    }

    public void addDestination(String s) {
        destinations.add(s);
    }

    public String getStartingLocation() {
        return startingLocation;
    }


    @Override
    public String toString() {
        return "Person{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", startingLocation='" + startingLocation + '\'' +
                ", destinations=" + destinations +
                '}';
    }
}
