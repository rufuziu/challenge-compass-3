package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.pool.PoolDTO;
import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.entities.Pool;
import br.demattos.iury.msproposals.repositories.PoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PoolService {
  private ModelMapper mapper;
  private PoolRepository repository;

  @Value("${proposalTime}")
  private int minutes;

  public PoolService(ModelMapper mapper, PoolRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public ProposalDTO createProposal(ProposalDTO proposalDTO) {
    proposalDTO.setId(null);
    Pool proposal = mapper.map(proposalDTO, Pool.class);
    var now = LocalDateTime.now();
    proposal.setInitTime(now);
    if (proposal.getCloseTime() == null ||
            proposal.getCloseTime().isBefore(proposal.getInitTime()))
      proposal.setCloseTime(now.plusMinutes(minutes));

    return mapper.map(repository.save(proposal), ProposalDTO.class);
  }

  public List<ProposalDTO> getAllOpenedProposals(){
    return Arrays.asList(mapper.map(
            repository.findAll()
            , ProposalDTO[].class));
  }
  public boolean haveProposalEnded() {
    return repository.existsByCloseTimeLessThan(LocalDateTime.now());
  }

  public List<PoolDTO> getAllEndedProposals() {
    List<Pool> list = repository
            .findAllByCloseTimeLessThan(LocalDateTime.now());
    return Arrays.asList(mapper.map(list, PoolDTO[].class));
  }

  public void removePollProposal(Long id) {
    repository.deleteById(id);
  }
}
