package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Pool;
import br.demattos.iury.msproposals.exceptions.proposal_exce.ProposalAlreadyExistsException;
import jakarta.validation.UnexpectedTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static br.demattos.iury.msproposals.common.ProposalConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PoolRepositoryTest {
  @Autowired
  private PoolRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;


  @Test
  void savePool_withValidData_ReturnsPool() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(VALID_PROPOSALDTO1, Pool.class);
    entity.setInitTime(now);
    //then
    Pool sut = repository.save(entity);
    //assert
    assertAll("PoolRepository save valid pool",
            () -> assertEquals(7L, sut.getId()),
            () -> assertEquals(entity.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(entity.getCloseTime(),
                    sut.getCloseTime())
    );
  }

  @Test
  void savePool_withInvalidData_ThrowsDataIntegrityViolationException() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(INVALID_PROPOSALDTO, Pool.class);
    entity.setInitTime(now);
    //then
    //assert
    assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(entity));
  }

  @Test
  void savePool_withExistingData_ThrowsDataIntegrityViolationException() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(VALID_PROPOSALDTO4, Pool.class);
    entity.setInitTime(now);
    //then
    Pool savedPool = testEntityManager.persistAndFlush(entity);
    testEntityManager.detach(savedPool);
    savedPool.setId(null);
    //assert
    assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(savedPool));
  }

  @Test
  void existsPool_ByCloseTimeLessThanTime_ReturnsTrue() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity = mapper.map(VALID_PROPOSALDTO1, Pool.class);
    entity.setInitTime(now);
    LocalDateTime time = VALID_PROPOSALDTO1
            .getCloseTime()
            .plusMinutes(10);
    //then
    Pool savedPool = repository.save(entity);
    //assert
    assertTrue(repository.existsByCloseTimeLessThan(time));
  }

  @Test
  void notExistsPool_ByCloseTimeLessThanTime_ReturnsFalse() {
    //given
    LocalDateTime time = VALID_PROPOSALDTO1
            .getCloseTime()
            .plusMinutes(10);
    //then
    //assert
    assertFalse(repository.existsByCloseTimeLessThan(time));
  }

  @Test
  void findAllByCloseTimeLessThan_ReturnsPoolList() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Pool entity1 = mapper.map(VALID_PROPOSALDTO1, Pool.class);
    Pool entity2 = mapper.map(VALID_PROPOSALDTO2, Pool.class);
    Pool entity3 = mapper.map(VALID_PROPOSALDTO3, Pool.class);
    Pool entity4 = mapper.map(VALID_PROPOSALDTO4, Pool.class);
    entity1.setInitTime(now);
    entity2.setInitTime(now);
    entity3.setInitTime(now);
    entity4.setInitTime(now);

    LocalDateTime time = VALID_PROPOSALDTO1
            .getCloseTime()
            .plusMinutes(10);
    //then
    repository.save(entity1);
    repository.save(entity2);
    repository.save(entity3);
    repository.save(entity4);
    List<Pool> list = repository.findAllByCloseTimeLessThan(time);
    //assert
    assertAll("PoolRepository find all closed proposals",
            () -> assertEquals(4, list.size()),
            () -> assertEquals(5L, list.get(3).getId())
    );
  }
}