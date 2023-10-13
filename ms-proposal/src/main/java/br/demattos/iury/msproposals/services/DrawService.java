package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.approved.ApprovedDTO;
import br.demattos.iury.msproposals.dto.draw.DrawDTO;
import br.demattos.iury.msproposals.entities.Approved;
import br.demattos.iury.msproposals.entities.Draw;
import br.demattos.iury.msproposals.repositories.ApprovedRepository;
import br.demattos.iury.msproposals.repositories.DrawRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DrawService {
  private ModelMapper mapper;
  private DrawRepository repository;

  public DrawService(ModelMapper mapper,
                     DrawRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public void createDraw(DrawDTO drawDTO) {
    drawDTO.setId(null);
    Draw proposal = mapper.map(drawDTO, Draw.class);
    repository.save(proposal);
  }
}
