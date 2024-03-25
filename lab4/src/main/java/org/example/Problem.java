package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * represents a ride-sharing problem
 * one driver can take one passenger at a time
 * task: assign as many passengers to drivers
 */
public class Problem {
    Faker faker = new Faker();
    List<Person> personList = new ArrayList<>();
    List<String> destinations = new ArrayList<>();

    /**
     * generates a random input of the problem, comprised of people and cities
     * @param personCount the number of people (random number of passengers and drivers)
     * @param destinationCount the number of destinations
     */
    public void generateProblem(int personCount, int destinationCount) {
        personList.clear();
        destinations.clear();
        for(int i=0;i<destinationCount;i++) {
            destinations.add(faker.address().city());
        }
        for(int i=0;i<personCount;i++) {
            PersonType type = PersonType.PASSENGER;
            double rand = Math.random();
            if(rand >= 0.5)
                type = PersonType.DRIVER;
            int startIndex = (int) (Math.random() * destinations.size());
            Person p = new Person(type, faker.name().fullName(), (int) (Math.random() * 100), destinations.get(startIndex));
            int count = 1;
            if(type == PersonType.DRIVER)
                count = (int)(Math.random() * 3+2);
            for(int j=0;j<count;j++) {
                int index = (int) (Math.random() * destinations.size());
                while(index == startIndex) {
                    index = (int) (Math.random() * destinations.size());
                }
                p.addDestination(destinations.get(index));
            }
            personList.add(p);
        }
    }
    public void printProblem() {
        System.out.println("Destinations: ");
        for(String d : destinations) {
            System.out.println(d);
        }
        System.out.println("Persons: ");
        for(Person p : personList) {
            System.out.println(p);
        }
    }

    /**
     * a list of all destinations that at least one driver passes through
     * @return list
     */
    public List<String> getAllDriverDestinations() {
        return personList.stream()
                .filter(person -> person.getType() == PersonType.DRIVER)
                .flatMap(driver -> driver.getDestinations().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * computes a map representing which people want to go to a certain destination
     * @return map
     */
    public Map<String, List<Person>> getDestinationPassengerMap() {
        return personList.stream()
                .filter(person -> person.getType() == PersonType.PASSENGER)
                .flatMap(passenger -> passenger.getDestinations().stream()
                        .map(destination -> new AbstractMap.SimpleEntry<>(destination, passenger)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    /**
     * filtered list of the people who are drivers
     * @return list
     */
    public List<Person> getDrivers() {
        return personList.stream()
                .filter(person -> person.getType() == PersonType.DRIVER)
                .collect(Collectors.toList());
    }

    /**
     * filtered list of the people who are passengers
     * @return list
     */
    public List<Person> getPassengers() {
        return personList.stream()
                .filter(person -> person.getType() == PersonType.PASSENGER)
                .collect(Collectors.toList());
    }

    public void addPerson(Person p) {
        personList.add(p);
    }


    /**
     * greedy solution of algorithm
     * for every driver, it sorts the remaining passengers and simulates the driver taking as many passengers on the way as possible
     */
    public void greedy() {
        // i have a list of all driver destinations
        // i have a map of destination -> passenger
        int numberOfMatchings = 0;
        List<Person> drivers = this.getDrivers();
        List<Person> passengers = this.getPassengers();
        System.out.println("Assignments: ");
        for(Person driver : drivers) {
            List<String> hisDestinations = driver.getDestinations();
            // sort passengers based on the index of their startingLocation in hisDestinations
            List<Person> peopleWhoWantToGo = passengers.stream()
                    .filter(p -> hisDestinations.contains(p.getDestinations().get(0)))
                    .filter(p -> hisDestinations.contains(p.getStartingLocation()))
                    .sorted(Comparator.comparingInt(p -> hisDestinations.indexOf(p.getStartingLocation())))
                    .toList();
            int currentDriverLocation = 0;
            for(Person p : peopleWhoWantToGo) {
                int x = hisDestinations.indexOf(p.getStartingLocation());
                int y = hisDestinations.indexOf(p.getDestinations().get(0));
                if(x>=currentDriverLocation && y >= currentDriverLocation && y > x) {
                    System.out.println("assignment: " + p + " to " + driver);
                    numberOfMatchings += 1;
                    currentDriverLocation = y;
                    passengers.remove(p);
                }
            }
        }
        System.out.println("Matchings with Greedy: " + numberOfMatchings);
        System.out.println("Passengers without cars: " + passengers);
    }
}
