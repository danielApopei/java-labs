import java.time.LocalDate;
import java.util.List;

public class TravelPlan {
    private Trip trip;

    TravelPlan(Trip trip) {
        this.trip = trip;
    }

    public void printPlan() {
        System.out.println(this);
    }

    public void addDayPlan(LocalDate of, List<Attraction> dayVisits) {
    }
}
