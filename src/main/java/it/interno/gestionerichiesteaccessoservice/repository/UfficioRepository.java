package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Ufficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UfficioRepository extends JpaRepository<Ufficio, String> {

    @Query("FROM Ufficio u WHERE u.codiceUfficio = ?1 AND u.dataCancellazione IS NULL")
    Ufficio getUfficioById(String codiceUfficio);

}
