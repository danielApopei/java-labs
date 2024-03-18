import objects.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * class that lists all the attractions that are planned to be visited in a city, along with start and end time
 */
public class Trip {
    private String city;
    private LocalDate start, end;
    Trip(String city){
        this.city = city;
    }
    private List<Attraction> attractionList = new ArrayList<>();

    /**
     * displays all attractions in a trip that are visitable, but free
     * @param dateToSortBy the date by which they are to be sorted (openingHour)
     */
    public void displayVisitableNotPayable(LocalDate dateToSortBy) {
//        LocalDate dateToSortBy = this.start;
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
        System.out.println("Visitable and Not Payable: ");
        for(Attraction a: list) {
            System.out.println(a);
        }
    }

    public void addAttraction(Attraction attraction) {
        attractionList.add(attraction);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractionList=" + attractionList +
                '}';
    }
}
