package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.StatoRichiesta;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatoRichiestaRepository extends JpaRepository<StatoRichiesta, Integer> {

    @NotNull
    @Override
    @Query("FROM StatoRichiesta s ORDER BY s.idStato ASC")
    List<StatoRichiesta> findAll();
}
