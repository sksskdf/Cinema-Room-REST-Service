package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int total_rows = 9;

    private int total_columns = 9;

    private List<Seat> available_seats = new ArrayList<>();

    {
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i, j);
                seat.setAvailable(true);
                if (i <= 4) {
                    seat.setPrice(10);
                } else {
                    seat.setPrice(8);
                }
                available_seats.add(seat);
            }
        }
    }

    public Cinema() {

    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

}
