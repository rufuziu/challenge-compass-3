package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.approved.ApprovedDTO;
import br.demattos.iury.msproposals.dto.pool.PoolDTO;
import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.entities.Approved;
import br.demattos.iury.msproposals.entities.Pool;
import br.demattos.iury.msproposals.repositories.ApprovedRepository;
import br.demattos.iury.msproposals.repositories.PoolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ApprovedService {
  private ModelMapper mapper;
  private ApprovedRepository repository;

  public ApprovedService(ModelMapper mapper,
                         ApprovedRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public void createApproved(ApprovedDTO approvedDTO) {
    approvedDTO.setId(null);
    Approved proposal = mapper.map(approvedDTO, Approved.class);
    repository.save(proposal);
  }
}
