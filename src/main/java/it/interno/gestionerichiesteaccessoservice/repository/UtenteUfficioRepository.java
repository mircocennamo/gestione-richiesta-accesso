package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.UtenteUfficio;
import it.interno.gestionerichiesteaccessoservice.entity.pk.UtenteUfficioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtenteUfficioRepository extends JpaRepository<UtenteUfficio, UtenteUfficioPK> {

    @Query("FROM UtenteUfficio u " +
            "WHERE TRIM(u.idUtente) = TRIM(?1) " +
            "AND u.dataCancellazione IS NULL")
    UtenteUfficio getByIdUtente(String idUtente);

}
