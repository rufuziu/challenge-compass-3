package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  Boolean existsByProposalIdAndEmployeeCpf(Long proposalId,
                                           String employeeCpf);
}
