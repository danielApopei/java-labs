package objects;

import objects.Attraction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * represents a Concert (type of Attraction); payable and visitable
 */
public class Concert extends Attraction implements Visitable, Payable {
    private double ticketPrice;
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
    public double getTicketPrice() {
        return ticketPrice;
    }
}
