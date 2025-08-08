package bwg.tutorial.book_lending.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookLendingStatusException extends RuntimeException {
    public BookLendingStatusException(String message) {
        super(message);
    }
}
