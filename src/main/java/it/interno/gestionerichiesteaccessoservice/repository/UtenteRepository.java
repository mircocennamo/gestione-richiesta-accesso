package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Utente;
import it.interno.gestionerichiesteaccessoservice.entity.pk.UtentePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtenteRepository extends JpaRepository<Utente, UtentePK> {

    @Query("FROM Utente u " +
            "WHERE u.dataCancellazione IS NULL " +
            "AND u.codiceFiscale = ?1 ")
    Utente getUtenteByCodiceFiscale(String codiceFiscale);

    @Query("FROM Utente u " +
            "WHERE u.dataCancellazione IS NULL " +
            "AND TRIM(u.idUtente) = TRIM(?1) ")
    Utente getUtenteByCodiceUtente(String codiceUtente);

}
