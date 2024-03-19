package org.example;


public class Person implements Comparable<Person> {
    private PersonType type;
    private String name;
    private int age;
    public PersonType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public Person(PersonType type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Person{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.getName());
    }
}
