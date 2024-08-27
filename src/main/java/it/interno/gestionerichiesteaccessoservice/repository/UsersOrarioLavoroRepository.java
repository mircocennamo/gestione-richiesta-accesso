package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.UsersOrarioLavoro;
import it.interno.gestionerichiesteaccessoservice.entity.pk.UsersOrarioLavoroPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersOrarioLavoroRepository extends JpaRepository<UsersOrarioLavoro, UsersOrarioLavoroPK> {

    @Query(value = "SELECT * FROM SSD_SECURITY.USERS_ORARIO_LAVORO u " +
            "WHERE u.G_MEMBER = ?1 " +
            "AND u.G_UFFICIO = ?2 " +
            "AND u.DATA_CAN IS NOT NULL " +
            "ORDER BY u.DATA_CAN DESC " +
            "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    UsersOrarioLavoro getOrarioLavoroByUtenteUfficio(String codiceUtente, String codiceUfficio);

}
