package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Approved;
import br.demattos.iury.msproposals.enums.EResult;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.demattos.iury.msproposals.common.ProposalConstants.APPROVED_POOLDTO;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApprovedRepositoryTest {
  @Autowired
  private ApprovedRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;

  @Test
  void createApproved_whenAchievedPoolCloseTime() {
//given
    Approved entity = mapper.map(APPROVED_POOLDTO, Approved.class);
    //then
    Approved savedAppr = repository.save(entity);
    //assert
    assertAll("ApprovedRepository saving approved",
            () -> assertEquals(1L, savedAppr.getId()),
            () -> assertTrue(savedAppr.getApprove() > savedAppr.getReject()),
            () -> assertEquals(EResult.APPROVED, savedAppr.getResult()));
  }
}