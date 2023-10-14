package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Draw;
import br.demattos.iury.msproposals.enums.EResult;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.demattos.iury.msproposals.common.ProposalConstants.DRAW_POOLDTO;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DrawRepositoryTest {
  @Autowired
  private DrawRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;

  @Test
  void createDraw_whenAchievedPoolCloseTime() {
//given
    Draw entity = mapper.map(DRAW_POOLDTO, Draw.class);
    //then
    Draw savedDraw = repository.save(entity);
    //assert
    assertAll("DrawRepository saving draw",
            () -> assertEquals(1L, savedDraw.getId()),
            () -> assertSame(savedDraw.getApprove(), savedDraw.getReject()),
            () -> assertEquals(EResult.DRAW, savedDraw.getResult()));
  }
}