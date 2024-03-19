package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(PersonType.DRIVER, "Dani", 24));
        persons.add(new Person(PersonType.PASSENGER, "Emil", 21));
        persons.add(new Person(PersonType.PASSENGER, "Vero", 20));
        persons.add(new Person(PersonType.DRIVER, "Andrei", 20));
        persons.add(new Person(PersonType.DRIVER, "Stefan", 12));
        persons.add(new Person(PersonType.PASSENGER, "Charles the IIIrd", 80));



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
}
