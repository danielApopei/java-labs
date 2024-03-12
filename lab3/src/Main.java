import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Museum museum = new Museum("whatevs");
        museum.setTicketPrice(20);

        Map<LocalDate, TimeInterval> museumTimetable = new HashMap<>();
        museumTimetable.put(LocalDate.of(2024, 2, 29), new TimeInterval(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        museum.setTimetable(museumTimetable);

        Church church = new Church("my church");

        Map<LocalDate, TimeInterval> churchTimetable = new HashMap<>();
        churchTimetable.put(LocalDate.of(2024, 2, 29), new TimeInterval(LocalTime.of(8, 0), LocalTime.of(18, 0)));
        church.setTimetable(churchTimetable);

        System.out.println(museum);
        System.out.println(church);
    }
}
