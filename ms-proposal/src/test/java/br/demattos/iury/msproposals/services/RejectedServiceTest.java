package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.rejected.RejectedDTO;
import br.demattos.iury.msproposals.entities.Rejected;
import br.demattos.iury.msproposals.repositories.RejectedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.demattos.iury.msproposals.common.ProposalConstants.REJECTED_POOLDTO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RejectedServiceTest {
  @InjectMocks
  private RejectedService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private RejectedRepository repository;

  @Test
  void createApproved_withApprovedDTO() {
    //given
    RejectedDTO dto = mapper.map(REJECTED_POOLDTO, RejectedDTO.class);
    Rejected entity = mapper.map(dto, Rejected.class);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    service.createRejected(dto);
    //assert
    assertAll("RejectedService create rejected",
            () -> assertEquals(1L, entity.getId()),
            () -> assertEquals(dto.getResult(), entity.getResult()));
  }
}