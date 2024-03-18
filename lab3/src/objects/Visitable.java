package objects;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * class that adds a Visitable propetrty (has a timetable when the Attraction is open for every day)
 */
public interface Visitable {
    Map<LocalDate, TimeInterval> timetable = new HashMap<>();

    public Map<LocalDate, TimeInterval> getTimetable();
    void addTimetable(LocalDate date, TimeInterval interval);
    default LocalTime getOpeningHour(LocalDate date) {
        return getTimetable().get(date).getLeft();
    }
}
