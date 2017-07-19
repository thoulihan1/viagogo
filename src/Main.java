import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Thomas on 7/19/17.
 */

public class Main {


    static int userX, userY;
    static HashMap<Integer, Event> events;

    public static void main(String[] args){

        events = new HashMap<>();

        //Generates 10 events with random coordinates and tickets
        for(int i=0; i<10; i++){
            generateEvent(i);
        }

        System.out.println();

        //User input for their coordinates
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your X coordinate: ");
        userX = scan.nextInt();
        while(userX<-10 || userX>10){
            System.out.print("X coordinate must be between -10 and 10. Try again: ");
            userX = scan.nextInt();
        }

        System.out.print("Enter your Y coordinate: ");
        userY = scan.nextInt();
        while(userY<-10 || userY>10){
            System.out.print("Y coordinate must be between -10 and 10. Try again: ");
            userY = scan.nextInt();
        }

        System.out.println("\nYour location is ("+userX+", "+userY+")\n\n");

        displayClosestFive(events);

        Event event = new Event(10, 10, null);
        System.out.println(getDist(event));
    }


    static void generateEvent(int i){
        ArrayList<Ticket> tickets  = new ArrayList<>();
        Random generator = new Random();

        //random amount of tickets (0-5)
        int amountOfTickets = generator.nextInt(5);

        //random cost of each ticket (50-70)
        for(int y=0; y<amountOfTickets; y++){
            tickets.add(new Ticket(70.0 - generator.nextInt(50)));
        }

        //random coords
        int x = 10 - generator.nextInt(20);
        int y = 10 - generator.nextInt(20);

        if(!coordinatesTaken(x, y)){
            Event event = new Event(x, y, tickets);
            events.put(i+1, event);
            System.out.println("Event " + (i+1) + " " + event.toString());

        } else {
            generateEvent(i);
        }
    }

    //Check whether an event already exists at the generated coordinates (each location can hold max 1 event)
    static boolean coordinatesTaken(int x, int y){
        boolean areCoordinatesTaken = false;

        for(HashMap.Entry<Integer, Event> entry : events.entrySet()) {
            if(x==entry.getValue().getX() && y==entry.getValue().getY()){
                return true;
            } else {
                areCoordinatesTaken = false;
            }
        }
        return areCoordinatesTaken;
    }

    //Shows the closest event, and removes it (so it won't be shown again)
    static void displayClosestFive(HashMap<Integer, Event> events){

        System.out.println("---Closest Five Events---");
        for(int i=0; i<5; i++) {

            //max distance (-10,-10) -> (10,10)
            int closestDist = 40;
            int closestId = 0;

            for (HashMap.Entry<Integer, Event> entry : events.entrySet()) {
                if (getDist(entry.getValue()) < closestDist) {
                    closestDist = getDist(entry.getValue());
                    closestId = entry.getKey();
                }
            }

            if(getCheapestTicketPrice(events.get(closestId))==0.0){
                System.out.println("Event " + closestId + " - (no tickets available), Distance: " + getDist(events.get(closestId)));
            } else {
                System.out.println("Event " + closestId + " - $"+ getCheapestTicketPrice(events.get(closestId)) + ", Distance: " + getDist(events.get(closestId)));
            }
            events.remove(closestId);
        }
    }

    //Calculate Manhattan distance
    static int getDist(Event e){
        return Math.abs(userX-e.getX()) + Math.abs(userY-e.getY());
    }

    static double getCheapestTicketPrice(Event e){
        ArrayList<Ticket> tickets = e.getTickets();

        if(tickets.isEmpty()){
            return 0.0;
        }

        double cheapest = tickets.get(0).getPrice();

        for(Ticket tick : tickets){
            if(tick.getPrice()<cheapest){
                cheapest = tick.getPrice();
            }
        }
        return cheapest;
    }

}
