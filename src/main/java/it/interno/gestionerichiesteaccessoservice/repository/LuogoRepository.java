package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface LuogoRepository extends JpaRepository<Luogo, Integer> {

    @Query("FROM Luogo l " +
            "WHERE TRIM(l.descrizioneLuogo) LIKE ?1% " +
            "AND ?3 >= l.dataInizioValidita " +
            "AND (?3 <= l.dataFineValidita OR l.dataFineValidita IS NULL) " +
            "AND l.inLuogo IN ?2 " +
            "AND l.tsCancellazione IS NULL")
    List<Luogo> getByDescrizioneLike(String descrizione, String[] inLuogo, LocalDate dataRif);

    @Query("FROM Luogo l " +
            "WHERE TRIM(l.descrizioneLuogo) = ?1 " +
            "AND ?3 >= l.dataInizioValidita " +
            "AND (?3 <= l.dataFineValidita OR l.dataFineValidita IS NULL) " +
            "AND l.inLuogo IN ?2 " +
            "AND l.tsCancellazione IS NULL")
    Luogo getByDescrizioneAndInLuogo(String descrizione, String[] inLuogo, LocalDate dataRif);

}
