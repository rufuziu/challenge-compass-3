package br.demattos.iury.msvoting.proxies;

import br.demattos.iury.msvoting.dtos.ProposalNewDTO;
import br.demattos.iury.msvoting.dtos.VoteDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "ms-proposal", path = "/api")
public interface ProposalResourceProxy {
  @GetMapping("/v1/proposals")
  public ResponseEntity<List<ProposalNewDTO>> getPoll();
  @PostMapping("/v1/votes")
  public ResponseEntity<Boolean> vote(
          @RequestBody @Valid
          VoteDTO voteDTO
  );
}
