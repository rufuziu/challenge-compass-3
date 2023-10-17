package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Proposal;
import br.demattos.iury.msproposals.enums.EResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
  Optional<Proposal> findByDescription(String description);
  Boolean existsByCloseTimeLessThan(LocalDateTime time);
  Boolean existsByIdAndResultAndCloseTimeLessThan(Long id,
                                                  EResult result,
                                                  LocalDateTime time);
  List<Proposal> findAllByResultAndCloseTimeLessThan(EResult result,
                                                     LocalDateTime time);
  List<Proposal> findAllByResult(EResult result);
}