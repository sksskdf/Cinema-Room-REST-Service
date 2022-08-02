package cinema;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Map<String, Object>> purchaseTicket(@RequestBody Seat requestedSeat) {

        if (requestedSeat.getRow() > 9 || requestedSeat.getColumn() > 9
                || requestedSeat.getRow() <= 0 || requestedSeat.getColumn() <= 0) {
            throw new SeatException("The number of a row or a column is out of bounds!");
        }

        for (Seat seat : cinema.getAvailable_seats()) {
            if (seat.getRow() == requestedSeat.getRow() && seat.getColumn() == requestedSeat.getColumn() && seat.isAvailable()) {
                seat.setAvailable(false);
                Map<String, Object> resultMap = new HashMap<>();
                String token = UUID.randomUUID().toString();
                resultMap.put("ticket", seat);
                resultMap.put("token", token);
                seat.setToken(token);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }

        throw new SeatException("The ticket has been already purchased!");

    }

    @PostMapping("/return")
    public ResponseEntity<Map<String, Object>> returnTicket(@RequestBody Map<String, String> token) {
        for (Seat seat : cinema.getAvailable_seats()) {
            if (seat.getToken().equals(token.get("token"))) {
                Map<String, Object> result = new HashMap<>();
                seat.setAvailable(true);
                seat.setToken("no token");
                result.put("returned_ticket", seat);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        }

        throw new SeatException("Wrong token!");
    }

    @PostMapping("/stats")
    public ResponseEntity<Stats> stats(@RequestParam(value = "password", required = false) String password) {
        if (password == null) {
            throw new UnauthorizeException("The password is wrong!");
        } else if (password.equals("super_secret")) {
            Stats stats = new Stats();
            List<Seat> available_seats = cinema.getAvailable_seats();

            stats.setCurrent_income(OptionalInt.of(available_seats.stream().filter(e -> !e.isAvailable())
                    .mapToInt(Seat::getPrice)
                    .sum()).orElse(0));

            stats.setNumber_of_available_seats((int)available_seats
                    .stream()
                    .filter(Seat::isAvailable)
                    .count());

            stats.setNumber_of_purchased_tickets(OptionalInt.of((int)available_seats
                    .stream()
                    .filter(e -> !e.isAvailable())
                    .count()).orElse(0));

            return new ResponseEntity<>(stats, HttpStatus.OK);
        }

        throw new RuntimeException();
    }
}
