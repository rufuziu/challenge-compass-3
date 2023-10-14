package br.demattos.iury.msproposals.exceptions;

import br.demattos.iury.msproposals.exceptions.error.ErrorDetails;
import br.demattos.iury.msproposals.exceptions.proposal_exce.ProposalAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ProposalAlreadyExistsException.class)
  public final ResponseEntity<ErrorDetails> handleProposalAlreadyExistsException(
          Exception ex,
          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            request.getDescription(false)
    );
    return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
  }
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request) {
    String errors = "";
    for (int i = 0; i < ex.getErrorCount(); i++) {
      errors = errors.concat(ex.getFieldErrors().get(i).getDefaultMessage().concat(", "));
    }
    ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(),
            errors,
            request.getDescription(false)
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
