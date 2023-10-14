package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.approved.ApprovedDTO;
import br.demattos.iury.msproposals.entities.Approved;
import br.demattos.iury.msproposals.repositories.ApprovedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.demattos.iury.msproposals.common.ProposalConstants.APPROVED_POOLDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApprovedServiceTest {
  @InjectMocks
  private ApprovedService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private ApprovedRepository repository;
  @Test
  void createApproved_withApprovedDTO() {
    //given
    ApprovedDTO dto = mapper.map(APPROVED_POOLDTO,ApprovedDTO.class);
    Approved entity = mapper.map(dto, Approved.class);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    service.createApproved(dto);
    //assert
    assertAll("ApprovedService create approved",
            ()->assertEquals(1L,entity.getId()),
            ()->assertEquals(dto.getResult(),entity.getResult()));
  }
}