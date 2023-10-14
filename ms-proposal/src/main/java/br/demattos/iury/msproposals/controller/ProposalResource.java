package br.demattos.iury.msproposals.controller;
import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.services.PoolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProposalResource {
  private final PoolService poolService;
  public ProposalResource(PoolService poolService) {
    this.poolService = poolService;
  }
  @PostMapping("v1/proposals")
  public ResponseEntity<ProposalDTO> create(
          @RequestBody @Valid ProposalDTO proposalDTO){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(poolService.createProposal(proposalDTO));
  }
  @GetMapping("v1/proposals")
  public ResponseEntity<List<ProposalDTO>> getAll(){
    return ResponseEntity.ok(poolService.getAllOpenedProposals());
  }
}