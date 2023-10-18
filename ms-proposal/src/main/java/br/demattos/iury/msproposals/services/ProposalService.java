package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.ProposalDTO;
import br.demattos.iury.msproposals.dto.ProposalNewDTO;
import br.demattos.iury.msproposals.entities.Proposal;
import br.demattos.iury.msproposals.enums.EResult;
import br.demattos.iury.msproposals.exceptions.proposal_exce.ProposalAlreadyExistsException;
import br.demattos.iury.msproposals.repositories.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {
  private ModelMapper mapper;
  private ProposalRepository repository;

  @Value("${proposalTime}")
  private int minutes;

  public ProposalService(ModelMapper mapper,
                         ProposalRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public ProposalNewDTO createProposal(ProposalNewDTO proposalNewDTO) {
    if (repository
            .findByDescription(proposalNewDTO.getDescription())
            .isPresent()) {
      throw new ProposalAlreadyExistsException("Description already in use.");
    }
    proposalNewDTO.setId(null);
    Proposal proposal = mapper.map(proposalNewDTO, Proposal.class);
    var now = LocalDateTime.now();
    proposal.setInitTime(now);
    if (proposal.getCloseTime() == null ||
            proposal.getCloseTime().isBefore(proposal.getInitTime()))
      proposal.setCloseTime(now.plusMinutes(minutes));

    return mapper.map(repository.save(proposal), ProposalNewDTO.class);
  }

  public List<ProposalNewDTO> getPollingProposals() {
    return Arrays.asList(mapper.map(
            repository.findAllByResult(EResult.POLLING)
            , ProposalNewDTO[].class));
  }

  public Boolean hasAnyProposalEnded() {
    return repository.existsByCloseTimeLessThan(LocalDateTime.now());
  }

  public List<ProposalDTO> getAllEndedProposals() {
    List<Proposal> list = repository
            .findAllByResultAndCloseTimeLessThan(EResult.POLLING,
                    LocalDateTime.now());
    return Arrays.asList(mapper.map(list, ProposalDTO[].class));
  }

  public Boolean checkProposalIsAbleToVote(Long id) {
    return repository.existsByIdAndResultAndCloseTimeGreaterThan(
            id,
            EResult.POLLING,
            LocalDateTime.now());
  }

  public void updateProposal(ProposalDTO proposalDTO) {
    Optional<Proposal> updatedEntity =
            repository.findById(proposalDTO.getId());
    if (updatedEntity.isPresent()) {
      updatedEntity.get().setApprove(proposalDTO.getApprove());
      updatedEntity.get().setReject(proposalDTO.getReject());
      updatedEntity.get().setResult(proposalDTO.getResult());
      repository.save(updatedEntity.get());
    }
  }

  public Boolean findProposalById(Long id) {
    return repository.existsById(id);
  }
}
