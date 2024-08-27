package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.ForzaPolizia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForzaPoliziaRepository extends JpaRepository<ForzaPolizia, Integer> {

    @Query(value = "SELECT sfp.* " +
            "FROM SSD_SECURITY.SEC_FORZA_POLIZIA sfp INNER JOIN SSD_SECURITY.SEC_FORZA_POLIZIA_MAPPING sfpm ON sfp.GROUP_ID = sfpm.GROUP_ID " +
            "ORDER BY sfp.ORDER_ID ASC ", nativeQuery = true)
    List<ForzaPolizia> getForzePoliziaConMapping();

}
