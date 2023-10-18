package br.demattos.iury.msvoting.services;

import br.demattos.iury.msvoting.dtos.ProposalNewDTO;
import br.demattos.iury.msvoting.dtos.VoteDTO;
import br.demattos.iury.msvoting.exceptions.proposal_exce.ProposalNotAbleToVoteException;
import br.demattos.iury.msvoting.exceptions.proposal_exce.ProposalNotExistsException;
import br.demattos.iury.msvoting.exceptions.vote_exce.VoteEmployeeAlreadyVotedException;
import br.demattos.iury.msvoting.proxies.ProposalResourceProxy;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalResourceService {
  private final ProposalResourceProxy proposalResourceProxy;

  public ProposalResourceService(ProposalResourceProxy proposalResourceProxy) {
    this.proposalResourceProxy = proposalResourceProxy;
  }

  public ResponseEntity<List<ProposalNewDTO>> getPoll() {
    return proposalResourceProxy.getPoll();
  }

  public ResponseEntity<Void> vote(VoteDTO voteDTO) {
    try {
      return proposalResourceProxy.vote(voteDTO);
    } catch (FeignException.FeignClientException e) {
      switch (e.status()) {
        case 400:
          throw new ProposalNotAbleToVoteException("Proposta não está disponível para votar");
        case 404:
          throw new ProposalNotExistsException("Id de proposta inválido");
        case 409:
          String message = new StringBuilder()
                  .append("Este colaborador já votou CPF: ")
                  .append(voteDTO.getEmployeeCpf())
                  .toString();
          throw new
                  VoteEmployeeAlreadyVotedException(message);
      }
    }
    return null;
  }
}
