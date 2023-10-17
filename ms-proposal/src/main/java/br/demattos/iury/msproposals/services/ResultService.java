package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.ProposalDTO;
import br.demattos.iury.msproposals.enums.EResult;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
  private ModelMapper mapper;
  private ProposalService proposalService;


  public ResultService(ProposalService proposalService,
                       ModelMapper mapper) {
    this.proposalService = proposalService;
    this.mapper = mapper;
  }

  @Scheduled(fixedRateString = "${intervalTime}")
  public void closeEndedProposals() {
    if (proposalService.hasAnyProposalEnded()) {
      generateResult();
    }
  }

  public void generateResult() {
    List<ProposalDTO> list = proposalService.getAllEndedProposals();
    for(ProposalDTO p: list){
      p.getVotes().stream().forEach(votes ->{
        if(votes.getVote())p.setApprove(1L);
        else p.setReject(1L);
      });
    }
    for (ProposalDTO p : list) {
      if (p.getApprove() > p.getReject())
        p.setResult(EResult.APPROVED);
      else if (p.getReject() > p.getApprove())
        p.setResult(EResult.REJECTED);
      else p.setResult(EResult.DRAW);
    }
    for (ProposalDTO p : list) {
      switch (p.getResult()) {
        case APPROVED:
          proposalService.updateProposal(p);
          break;
        case REJECTED:
          proposalService.updateProposal(p);
          break;
        case DRAW:
          proposalService.updateProposal(p);
          break;
      }
    }
  }
}
