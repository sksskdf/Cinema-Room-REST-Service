package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Seat {
    private int row;

    private int column;

    private int price;

    private boolean isAvailable;

    private String token = "no token";

    public Seat() {

    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
