import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Trip {
    private String city;
    private LocalDate start, end;
    Trip(String city){
        this.city = city;
    }
    private List<Attraction> attractionList = new ArrayList<>();
    public void displayVisitableNotPayable() {
        LocalDate dateToSortBy = this.start;
        ArrayList<Attraction> list = new ArrayList<>();
        for(Attraction a : attractionList) {
            if(a instanceof Visitable && !(a instanceof Payable)) {
                list.add(a);
            }
        }
        list.sort(new Comparator<Attraction>() {
            @Override
            public int compare(Attraction o1, Attraction o2) {
                Visitable v1 = (Visitable) o1;
                Visitable v2 = (Visitable) o2;
                return v1.getOpeningHour(dateToSortBy).compareTo(v2.getOpeningHour(dateToSortBy));
            }
        });
        for(Attraction a: list) {
            System.out.println(a);
        }
    }

    public void addAttraction(Attraction attraction) {

    }
}
