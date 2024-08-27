package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.FileRichiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRichiestaRepository extends JpaRepository<FileRichiesta, Integer> {

    @Query("FROM FileRichiesta f JOIN f.richiestaRegistrazione r " +
            "WHERE r.idProtocollo = ?1")
    FileRichiesta getByProtocollo(String potocollo);

    @Query(value = "SELECT MAX(f.ID) + 1 " +
            "FROM SSD_SECURITY.SEC_FILE_RICHIESTA f", nativeQuery = true)
    Long getNextId();

    @Query(value = "DELETE FROM SSD_SECURITY.SEC_FILE_RICHIESTA f " +
            "WHERE f.PROTOCOLLO = ?1", nativeQuery = true)
    void deleteFileByProtocollo(String protocollo);
}
