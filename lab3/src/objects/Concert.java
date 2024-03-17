package objects;

import objects.Attraction;

import java.time.LocalDate;
import java.util.Map;

/**
 * represents a Concert (type of Attraction); payable and visitable
 */
public class Concert extends Attraction implements Visitable, Payable {
    private double ticketPrice;
    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return Visitable.timetable;
    }

    @Override
    public void addTimetable(LocalDate date, TimeInterval interval) {
        Visitable.timetable.put(date, interval);
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }
}
