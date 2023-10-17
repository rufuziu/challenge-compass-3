package br.demattos.iury.msproposals.exceptions.proposal_exce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProposalNotExistsException extends RuntimeException {
  public ProposalNotExistsException(String message) {
    super(message);
  }
}
