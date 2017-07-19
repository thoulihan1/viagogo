import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 7/19/17.
 */
public class Event {

    int x, y;
    ArrayList<Ticket> tickets;

    public Event(){

    }

    public Event(int x, int y, ArrayList tickets) {
        this.x = x;
        this.y = y;
        this.tickets = tickets;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String toString(){
        String ret = ("("+x+","+y+"). Tickets: ");

        for(Ticket t : tickets){
            ret += "$"+t.getPrice() + ", ";
        }

        if(!tickets.isEmpty()){
            ret = ret.substring(0, ret.length()-2);

        }

        return ret;

    }
}
