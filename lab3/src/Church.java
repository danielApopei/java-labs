import java.time.LocalDate;
import java.util.Map;

public class Church extends Attraction implements Visitable{

    Church(String name){
        this.name = name;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return null;
    }

    @Override
    public void setTimetable(Map<LocalDate, TimeInterval> m) {

    }

    @Override
    public String toString() {
        return "Church{}";
    }
}
