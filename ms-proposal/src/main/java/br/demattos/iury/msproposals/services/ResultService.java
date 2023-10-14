package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.approved.ApprovedDTO;
import br.demattos.iury.msproposals.dto.draw.DrawDTO;
import br.demattos.iury.msproposals.dto.pool.PoolDTO;
import br.demattos.iury.msproposals.dto.rejected.RejectedDTO;
import br.demattos.iury.msproposals.enums.EResult;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
  private ModelMapper mapper;
  private PoolService poolService;
  private ApprovedService approvedService;
  private RejectedService rejectedService;
  private DrawService drawService;

  public ResultService(PoolService poolService,
                       DrawService drawService,
                       RejectedService rejectedService,
                       ApprovedService approvedService,
                       ModelMapper mapper) {
    this.poolService = poolService;
    this.drawService = drawService;
    this.rejectedService = rejectedService;
    this.approvedService = approvedService;
    this.mapper = mapper;
  }

  @Scheduled(fixedRateString = "${intervalTime}")
  public void closeEndedProposals() {
    if (poolService.haveProposalEnded()) {
      generateResult();
    }
  }
  public void generateResult(){
    List<PoolDTO> list = poolService.getAllEndedProposals();
    for (PoolDTO p : list) p.setSumResult();
    for (PoolDTO p: list){
      if(p.getSumResult()>0) p.setResult(EResult.APPROVED);
      else if(p.getSumResult()<0) p.setResult(EResult.REJECTED);
      else p.setResult(EResult.DRAW);
    }
    for (PoolDTO p : list) {
      switch (p.getResult()) {
        case APPROVED:
          approvedService.createApproved(
                  mapper.map(p, ApprovedDTO.class));
          poolService.removePollProposal(p.getId());
          break;
        case REJECTED:
          rejectedService.createRejected(
                  mapper.map(p, RejectedDTO.class));
          poolService.removePollProposal(p.getId());
          break;
        case DRAW:
          drawService.createDraw(mapper.map(p, DrawDTO.class));
          poolService.removePollProposal(p.getId());
          break;
      }
    }
  }
}
