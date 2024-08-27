package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Funzione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunzioneRepository extends JpaRepository<Funzione, String> {

    @Query("FROM Funzione f WHERE f.dataCancellazione IS NULL ORDER BY f.funzione ASC")
    List<Funzione> getAllFunzioni();

    @Query(value = "SELECT sf.* " +
            "FROM SSD_SECURITY.SEC_FUNZIONE sf INNER JOIN SSD_SECURITY.SEC_FUNZIONE_QUALIFICA_MAPPING sfqm ON sf.ID_FUNZIONE = sfqm.ID_FUNZIONE " +
            "WHERE sfqm.CODICA_QUALIFICA_PROFILO = ?1", nativeQuery = true)
    Funzione getFunzioneByMappingQualificaProfilo(String codiceQualificaProfilo);

}
