package objects;

import objects.Attraction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * represents a Statue (type of Attraction); visitable, not payable
 */
public class Statue extends Attraction implements Visitable {
    public Statue(String name) {
        this.name = name;
    }
    public Map<LocalDate, TimeInterval> timetable = new HashMap<>();
    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }

    @Override
    public void addTimetable(LocalDate date, TimeInterval interval) {
        this.timetable.put(date, interval);
    }

    @Override
    public String toString() {
        return "Statue{" +
                "name='" + name + '\'' +
                '}';
    }
}
