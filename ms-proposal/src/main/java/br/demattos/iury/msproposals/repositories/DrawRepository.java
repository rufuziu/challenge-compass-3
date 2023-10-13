package br.demattos.iury.msproposals.repositories;

import br.demattos.iury.msproposals.entities.Draw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrawRepository extends JpaRepository<Draw,Long> {
}
