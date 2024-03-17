import objects.Attraction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * a class that specifies the exact scheduling of a trip.
 * specifies on each day each attraction is visited
 */
public class TravelPlan {
    private Trip trip;
    private Map<Attraction, LocalDate> schedule = new HashMap<>();
    TravelPlan(Trip trip) {
        this.trip = trip;
    }

    /**
     * prints out all the scheduling
     */
    public void printPlan() {
        System.out.println("TravelPlan schedule: ");
        for(Map.Entry<Attraction, LocalDate> entry : schedule.entrySet()) {
            Attraction attraction = entry.getKey();
            LocalDate date = entry.getValue();
            System.out.println(attraction.name + " will be visited on " + date);
        }
    }

    /**
     * plans for an attraction to be visited on a set date
     * @param attraction attraction
     * @param date date
     */
    public void addDayPlan(Attraction attraction, LocalDate date) {
        schedule.put(attraction, date);
    }
}
