package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.ComandanteUffici;
import it.interno.gestionerichiesteaccessoservice.entity.pk.ComandanteUfficiPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComandanteUfficiRepository extends JpaRepository<ComandanteUffici, ComandanteUfficiPK> {

    @Query("FROM ComandanteUffici c WHERE c.codiceUfficio = ?1 AND c.dataCancellazione IS NULL")
    ComandanteUffici getComandanteByUfficio(String codiceUfficio);

}
