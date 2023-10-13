package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Rejected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedRepository extends JpaRepository<Rejected,Long> {
}
