package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.VoteDTO;
import br.demattos.iury.msproposals.entities.Vote;
import br.demattos.iury.msproposals.exceptions.vote_exce.VoteEmployeeAlreadyVotedException;
import br.demattos.iury.msproposals.repositories.VoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
  private ModelMapper mapper;
  private VoteRepository repository;

  public VoteService(ModelMapper mapper, VoteRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public void create(VoteDTO voteDTO) {
    if (repository.existsByProposalIdAndEmployeeCpf(
            voteDTO.getProposalId(),
            voteDTO.getEmployeeCpf())) {
      throw new
              VoteEmployeeAlreadyVotedException("Employee already voted in this proposal.");
    }
    repository.save(mapper.map(voteDTO, Vote.class));
  }
}
