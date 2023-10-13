package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PoolRepository extends JpaRepository<Pool,Long> {
  Boolean existsByCloseTimeLessThan(LocalDateTime time);
  List<Pool> findAllByCloseTimeLessThan(LocalDateTime time);
}
