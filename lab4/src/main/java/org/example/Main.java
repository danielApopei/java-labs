package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void compulsory() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(PersonType.DRIVER, "Dani", 24, "a"));
        persons.add(new Person(PersonType.PASSENGER, "Emil", 21,"b"));
        persons.add(new Person(PersonType.PASSENGER, "Vero", 20,"c"));
        persons.add(new Person(PersonType.DRIVER, "Andrei", 20,"d"));
        persons.add(new Person(PersonType.DRIVER, "Stefan", 12,"e"));
        persons.add(new Person(PersonType.PASSENGER, "Charles the IIIrd", 80,"f"));

        LinkedList<Person> drivers = persons.stream()
                .filter(person -> person.getType() == PersonType.DRIVER)
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("drivers: ");
        for(Person driver: drivers) {
            System.out.println(driver);
        }

        TreeSet<Person> passengers = persons.stream()
                .filter(person -> person.getType() == PersonType.PASSENGER)
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("passengers: ");
        for(Person passenger: passengers) {
            System.out.println(passenger);
        }
    }

    public static void main(String[] args) {
        Problem p = new Problem();
        p.generateProblem(25, 5);
        Person p1 = new Person(PersonType.DRIVER, "A",32, "A");
        p1.addDestination("A");
        p1.addDestination("B");
        p1.addDestination("C");
        p1.addDestination("D");
        Person p2 = new Person(PersonType.PASSENGER, "A1", 20, "A");
        p2.addDestination("B");
        Person p3 = new Person(PersonType.PASSENGER, "A2", 22, "C");
        p3.addDestination("D");

        p.addPerson(p1);
        p.addPerson(p2);
        p.addPerson(p3);
//        p.printProblem();

//        System.out.println(p.getDrivers());
//        System.out.println(p.getPassengers());

        List<String> driverDest = p.getAllDriverDestinations();
//        System.out.println(driverDest);

        Map<String, List<Person>> destinationPassengerMap = p.getDestinationPassengerMap();
//        System.out.println(destinationPassengerMap);

        p.greedy();
    }
}
