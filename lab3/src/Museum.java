import java.time.LocalDate;
import java.util.Map;

public class Museum extends Attraction implements Visitable, Payable{
    double ticketPrice;
    public double getTicketPrice() {
        return 0;
    }

    Museum(String name) {
        this.name = name;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return null;
    }

    @Override
    public void setTimetable(Map<LocalDate, TimeInterval> m) {

    }

    public void setTicketPrice(double newTicketPrice) {
        this.ticketPrice = newTicketPrice;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "ticketPrice=" + ticketPrice +
                '}';
    }
}
