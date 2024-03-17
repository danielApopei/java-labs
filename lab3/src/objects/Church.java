package objects;

import objects.Attraction;

import java.time.LocalDate;
import java.util.Map;

/**
 * represents a Church (type of Attraction); visitable, not payable
 */
public class Church extends Attraction implements Visitable {

    public Church(String name){
        this.name = name;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }

    @Override
    public void addTimetable(LocalDate date, TimeInterval interval) {
        Visitable.timetable.put(date, interval);
    }

    @Override
    public String toString() {
        return "Church{}" + this.getTimetable();
    }
}
