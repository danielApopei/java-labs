import java.time.LocalDate;
import java.util.Map;

public class Statue extends Attraction implements Visitable{
    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return null;
    }

    @Override
    public void setTimetable(Map<LocalDate, TimeInterval> newTimetable) {

    }
}
