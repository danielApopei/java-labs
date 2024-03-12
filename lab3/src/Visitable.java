import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {

    public Map<LocalDate,TimeInterval> getTimetable();
    void setTimetable(Map<LocalDate, TimeInterval> m);
    default LocalTime getOpeningHour(LocalDate date) {
        return getTimetable().get(date).getLeft(); // access left of the TimeInterval value, based on date key
    }
}
