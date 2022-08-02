package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatException extends RuntimeException{

    public SeatException(String cause) {
        super(cause);
    }
}
