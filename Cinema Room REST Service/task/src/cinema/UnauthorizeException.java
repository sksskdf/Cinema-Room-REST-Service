package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizeException extends RuntimeException{
    public UnauthorizeException(String error) {
        super(error);
    }
}
