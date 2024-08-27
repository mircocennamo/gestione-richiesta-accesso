package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.MotivazioneRifiuto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotivazioneRifiutoRepository extends JpaRepository<MotivazioneRifiuto, Integer> {

    @NotNull
    @Override
    @Query("FROM MotivazioneRifiuto m ORDER BY m.idMotivazione ASC")
    List<MotivazioneRifiuto> findAll();
}
