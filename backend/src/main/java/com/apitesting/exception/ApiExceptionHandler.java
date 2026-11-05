package com.apitesting.exception;
import com.apitesting.dto.Responses;import org.springframework.http.*;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;import org.springframework.web.server.ResponseStatusException;import java.time.Instant;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Responses.Error> validation(MethodArgumentNotValidException e){return error(400,"Validation failed");}
 @ExceptionHandler(ResponseStatusException.class) ResponseEntity<Responses.Error> status(ResponseStatusException e){return error(e.getStatusCode().value(),e.getReason());}
 @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class) ResponseEntity<Responses.Error> forbidden(Exception e){return error(403,"Forbidden");}
 private ResponseEntity<Responses.Error> error(int status,String msg){return ResponseEntity.status(status).body(new Responses.Error(HttpStatus.valueOf(status).getReasonPhrase(),msg,status,Instant.now()));}
}
