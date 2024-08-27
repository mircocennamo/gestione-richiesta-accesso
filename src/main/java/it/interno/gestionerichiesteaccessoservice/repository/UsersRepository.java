package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, String> {

    @Query(value = "SELECT * " +
            "FROM SSD_SECURITY.USERS u " +
            "WHERE u.CF = ?1 " +
            "AND u.DATA_CAN IS NOT NULL " +
            "ORDER BY u.DATA_CAN DESC " +
            "FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Users getUserByCodiceFiscale(String codiceFiscale);

}
