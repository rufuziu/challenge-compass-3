package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Rejected;
import br.demattos.iury.msproposals.enums.EResult;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.demattos.iury.msproposals.common.ProposalConstants.REJECTED_POOLDTO;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RejectedRepositoryTest {
  @Autowired
  private RejectedRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;

  @Test
  void createRejected_whenAchievedPoolCloseTime() {
//given
    Rejected entity = mapper.map(REJECTED_POOLDTO, Rejected.class);
    //then
    Rejected savedRej = repository.save(entity);
    //assert
    assertAll("RejectedRepository saving rejected",
            () -> assertEquals(1L, savedRej.getId()),
            () -> assertTrue(savedRej.getApprove() < savedRej.getReject()),
            () -> assertEquals(EResult.REJECTED, savedRej.getResult()));
  }
}