import objects.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Museum museum = new Museum("whatevs");
        museum.setTicketPrice(20);

        Map<LocalDate, TimeInterval> museumTimetable = new HashMap<>();
        museum.addTimetable(LocalDate.of(2024, 2, 29), new TimeInterval(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        museum.addTimetable(LocalDate.of(2024, 3, 1), new TimeInterval(LocalTime.of(8, 30), LocalTime.of(16, 0)));
        museum.setTicketPrice(20.3d);

        Church church = new Church("my church");

        Map<LocalDate, TimeInterval> churchTimetable = new HashMap<>();
        church.addTimetable(LocalDate.of(2024, 2, 29), new TimeInterval(LocalTime.of(8, 0), LocalTime.of(18, 0)));
        church.addTimetable(LocalDate.of(2023, 9, 10), new TimeInterval(LocalTime.of(7,49), LocalTime.of(9,49)));
        Statue statue = new Statue("Cuza statue");
        statue.addTimetable(LocalDate.of(2024, 2, 29), new TimeInterval(LocalTime.of(5, 0), LocalTime.of(11,0)));

        System.out.println(museum);
        System.out.println(statue);
        System.out.println(church);

        Trip trip = new Trip("Iasi");
        trip.addAttraction(museum);
        trip.addAttraction(church);
        trip.addAttraction(statue);

        trip.displayVisitableNotPayable(LocalDate.of(2024, 2, 29));
        System.out.println("Opening hour for museum: " + museum.getOpeningHour(LocalDate.of(2024, 2, 29)));

        TravelPlan travelPlan = new TravelPlan(trip);
        travelPlan.addDayPlan(museum, LocalDate.of(2024, 2, 29));
        travelPlan.addDayPlan(church, LocalDate.of(2024, 3, 2));
        travelPlan.printPlan();
    }

}
