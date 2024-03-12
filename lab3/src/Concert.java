import java.time.LocalDate;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable{
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;
    @Override
    public Map<LocalDate,TimeInterval> getTimetable() {
        return timetable;
    }

    @Override
    public void setTimetable(Map<LocalDate, TimeInterval> newTimetable) {
        this.timetable = newTimetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }
}
