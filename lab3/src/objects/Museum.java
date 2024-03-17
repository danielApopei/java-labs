package objects;

import java.time.LocalDate;
import java.util.Map;

/**
 * represents a Museum (type of Attraction); visitable and payable
 */
public class Museum extends Attraction implements Visitable, Payable {
    double ticketPrice;

    public double getTicketPrice() {
        return 0;
    }

    public Museum(String name) {
        this.name = name;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }

    @Override
    public void addTimetable(LocalDate date, TimeInterval interval) {
        timetable.put(date, interval);
    }

    public void setTicketPrice(double newTicketPrice) {
        this.ticketPrice = newTicketPrice;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "ticketPrice=" + ticketPrice + " " + timetable +
                '}';
    }
}
