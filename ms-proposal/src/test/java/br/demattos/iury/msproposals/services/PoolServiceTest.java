package br.demattos.iury.msproposals.services;

import br.demattos.iury.msproposals.dto.pool.PoolDTO;
import br.demattos.iury.msproposals.dto.proposal.ProposalDTO;
import br.demattos.iury.msproposals.entities.Pool;
import br.demattos.iury.msproposals.repositories.PoolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.demattos.iury.msproposals.common.ProposalConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PoolServiceTest {
  @InjectMocks
  private PoolService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private PoolRepository repository;

  @Test
  void createProposal_withCloseTimeProposalDTO_ReturnsProposalDTO() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(VALID_PROPOSALDTO1, Pool.class);
    entity.setId(1L);
    entity.setInitTime(now);
    when(repository.save(any())).thenReturn(entity);
    //then
    ProposalDTO sut = service.createProposal(VALID_PROPOSALDTO1);
    //assert
    assertAll("PoolService create pool with closeTime",
            () -> assertEquals(1L,
                    sut.getId()),
            () -> assertEquals(VALID_PROPOSALDTO1.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(VALID_PROPOSALDTO1.getCloseTime(),
                    sut.getCloseTime()));

  }

  @Test
  void createProposal_withNullCloseTimeProposalDTO_ReturnsProposalDTO() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(VALID_PROPOSALDTO_WITHOUTCLOSETIME,
            Pool.class);
    entity.setInitTime(now);
    entity.setCloseTime(now);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    ProposalDTO sut = service
            .createProposal(VALID_PROPOSALDTO_WITHOUTCLOSETIME);
    //assert
    assertAll("PoolService create pool with null closeTime",
            () -> assertEquals(1L,
                    sut.getId()),
            () -> assertEquals(VALID_PROPOSALDTO_WITHOUTCLOSETIME.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(now,
                    sut.getCloseTime()));

  }

  @Test
  void createProposal_withInvalidProposalDTO_ThrowsIllegalArgumentException() {
    //given
    //then
    //assert
    assertThrows(IllegalArgumentException.class,
            () -> service
                    .createProposal(INVALID_PROPOSALDTO));
  }

  @Test
  void getAllOpenedProposals_ReturnsProposalDTOList() {
    List<Pool> list = new ArrayList<>();
    list.add(mapper.map(VALID_PROPOSALDTO1,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO2,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO3,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO4,Pool.class));

    when(repository.findAll()).thenReturn(list);
    //then
    List<ProposalDTO> sut = service.getAllOpenedProposals();

    //assert
    assertAll("PoolRepository find all opened proposals",
            () -> assertEquals(4, sut.size()));
  }

  @Test
  void haveProposalEnded_ReturnsTrue() {
    when(repository.existsByCloseTimeLessThan(any()))
            .thenReturn(true);
    assertTrue(service.haveProposalEnded());
  }

  @Test
  void haveProposalEnded_ReturnsFalse() {
    when(repository.existsByCloseTimeLessThan(any()))
            .thenReturn(false);
    assertFalse(service.haveProposalEnded());
  }

  @Test
  void getAllEndedProposals_ReturnsPoolDTOList() {
    List<Pool> list = new ArrayList<>();
    list.add(mapper.map(VALID_PROPOSALDTO1,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO2,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO3,Pool.class));
    list.add(mapper.map(VALID_PROPOSALDTO4,Pool.class));
    when(repository.findAllByCloseTimeLessThan(any())).thenReturn(list);

    List<PoolDTO> sut = service.getAllEndedProposals();
    assertAll("PoolRepository find all closed proposals",
            () -> assertEquals(4, sut.size()));
  }

}