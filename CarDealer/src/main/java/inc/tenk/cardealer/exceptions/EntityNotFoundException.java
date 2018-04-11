package inc.tenk.cardealer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such entity found!")

public class EntityNotFoundException extends RuntimeException {

}