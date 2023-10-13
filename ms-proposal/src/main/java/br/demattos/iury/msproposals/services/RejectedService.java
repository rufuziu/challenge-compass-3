package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.draw.DrawDTO;
import br.demattos.iury.msproposals.dto.rejected.RejectedDTO;
import br.demattos.iury.msproposals.entities.Draw;
import br.demattos.iury.msproposals.entities.Rejected;
import br.demattos.iury.msproposals.repositories.DrawRepository;
import br.demattos.iury.msproposals.repositories.RejectedRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RejectedService {
  private ModelMapper mapper;
  private RejectedRepository repository;

  public RejectedService(ModelMapper mapper,
                         RejectedRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public void createRejected(RejectedDTO rejectedDTO) {
    rejectedDTO.setId(null);
    Rejected proposal = mapper.map(rejectedDTO, Rejected.class);
    repository.save(proposal);
  }
}
