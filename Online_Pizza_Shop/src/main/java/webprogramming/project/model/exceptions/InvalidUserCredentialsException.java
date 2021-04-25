package webprogramming.project.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super("Invalid Credentials");
    }
}