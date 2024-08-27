package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Qualifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QualificaRepository extends JpaRepository<Qualifica, Integer> {

    @Query(value = "SELECT * FROM SSD_SECURITY.SEC_QUALIFICA sq " +
            "WHERE sq.CATEGORIA = :categoria " +
            "AND ((:idEnte = 1 AND sq.POLIZIA_DI_STATO IS NOT NULL) " +
            "OR (:idEnte = 2 AND sq.ARMA_DEI_CARABINIERI IS NOT NULL) " +
            "OR (:idEnte = 3 AND sq.CORPO_DELLA_GUARDIA_DI_FINANZA IS NOT NULL) " +
            "OR (:idEnte = 4 AND sq.CORPO_DI_POLIZIA_PENITENZIARIA IS NOT NULL) " +
            "OR (:idEnte NOT IN (1,2,3,4) AND sq.PERSONALE_CIVILE IS NOT NULL)) " +
            "ORDER BY sq.QUALIFICA_ID ASC", nativeQuery = true)
    List<Qualifica> getQualificaByCategoriaAndEnte(String categoria, Integer idEnte);

    @Query(value = "SELECT DISTINCT sq.CATEGORIA " +
            "FROM SSD_SECURITY.SEC_QUALIFICA sq " +
            "WHERE " +
            "(:idEnte = 1 AND sq.POLIZIA_DI_STATO IS NOT NULL) " +
            "OR (:idEnte = 2 AND sq.ARMA_DEI_CARABINIERI IS NOT NULL) " +
            "OR (:idEnte = 3 AND sq.CORPO_DELLA_GUARDIA_DI_FINANZA IS NOT NULL) " +
            "OR (:idEnte = 4 AND sq.CORPO_DI_POLIZIA_PENITENZIARIA IS NOT NULL) " +
            "OR (:idEnte NOT IN (1,2,3,4) AND sq.PERSONALE_CIVILE IS NOT NULL) " +
            "ORDER BY sq.CATEGORIA ASC", nativeQuery = true)
    List<String> getCategoriaQualificaByEnte(Integer idEnte);

    @Query("SELECT DISTINCT ruolo " +
            "FROM Qualifica " +
            "ORDER BY ruolo ASC")
    List<String> getRuoloQualifica();
}
