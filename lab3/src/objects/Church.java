package objects;

import objects.Attraction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * represents a Church (type of Attraction); visitable, not payable
 */
public class Church extends Attraction implements Visitable {
    public Church(String name){
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
        return "Church{}" + this.getTimetable();
    }
}
