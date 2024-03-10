package objects;

import utilities.Tour;

import java.util.ArrayList;

/**
 * class representing a solution to the VRP problem in the form of tours.
 * each tour represents the clients, in order, that each car visits for optimal time
 */
public class Solution {
    ArrayList<Tour> tours;
    public Solution() {}

    public Solution(ArrayList<Tour> tours){
        this.tours = tours;
    }

    /**
     * prints the tours on-screen
     */
    public void showSolution() {
        for(Tour t: tours)
            System.out.println(t);
    }
}
