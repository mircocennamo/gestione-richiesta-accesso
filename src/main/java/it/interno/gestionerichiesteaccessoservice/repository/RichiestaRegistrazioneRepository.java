package it.interno.gestionerichiesteaccessoservice.repository;

import it.interno.gestionerichiesteaccessoservice.entity.RichiestaRegistrazione;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RichiestaRegistrazioneRepository extends JpaRepository<RichiestaRegistrazione,String> {

    @Query(value = "SELECT SSD_SECURITY.GET_NEXTPROTOCOL FROM DUAL", nativeQuery = true)
    String getNextProtocollo();

    @Query("FROM RichiestaRegistrazione r JOIN r.statoRichiesta s " +
            "WHERE r.codiceFiscale = ?1 " +
            "AND s.idStato NOT IN (4, 5) " +
            "AND r.idProtocollo = ?2")
    RichiestaRegistrazione getRichiesta(String codiceFiscale, String protocollo);

    @Query("FROM RichiestaRegistrazione r JOIN r.statoRichiesta s " +
            "WHERE r.codiceFiscale = ?1 " +
            "AND s.idStato NOT IN (4, 5) ")
    List<RichiestaRegistrazione> getRichiestaByCodiceFiscale(String codiceFiscale);

    @Query("FROM RichiestaRegistrazione r JOIN r.statoRichiesta s " +
            "WHERE r.idProtocollo = ?1 " +
            "AND s.idStato != 4")
    RichiestaRegistrazione getRichiestaByProtocollo(String protocollo);

    @Query(value = "CALL SSD_SECURITY.UTILITY.SEND_MAIL(?1, ?2, ?3)",nativeQuery = true)
    void sendMail(String email, String subject, String messaggio);

    @Query(value = "SELECT * FROM SSD_SECURITY.V_LISTA_UTENTI_FP WHERE START_UFF = ?1", nativeQuery = true)
    List<Tuple> getFocalPointByUfficio(String codiceUfficio);

    @Query(value = "SELECT * FROM SSD_SECURITY.SEC_RICHIESTA_REGISTRAZIONE r " +
            "WHERE r.STATO = ?1 " +
            "AND (?2 IS NULL OR r.FORZA_POLIZIA = ?2) " +
            "AND (?3 IS NULL OR r.UFFICIO = ?3) " +
            "AND (?4 IS NULL OR r.NOME LIKE %?4% OR r.COGNOME LIKE %?4%) " +
            "AND (?6 = 'R_UFFICIO_SICUREZZA' OR r.UFFICIO IN (SELECT * FROM JSON_TABLE(SSD_SECURITY.GET_UFF_GER(?5),'$[*]' COLUMNS VAL VARCHAR PATH '$')))",
            countQuery = "SELECT * FROM SSD_SECURITY.SEC_RICHIESTA_REGISTRAZIONE r " +
                    "WHERE r.STATO = ?1 " +
                    "AND (?2 IS NULL OR r.FORZA_POLIZIA = ?2) " +
                    "AND (?3 IS NULL OR r.UFFICIO = ?3) " +
                    "AND (?4 IS NULL OR r.NOME LIKE %?4% OR r.COGNOME LIKE %?4%) " +
                    "AND (?6 = 'R_UFFICIO_SICUREZZA' OR r.UFFICIO IN (SELECT * FROM JSON_TABLE(SSD_SECURITY.GET_UFF_GER(?5),'$[*]' COLUMNS VAL VARCHAR PATH '$')))",
            nativeQuery = true)
    Page<RichiestaRegistrazione> getRichiesteByStatoFilrate(Integer idStato, Integer idForzaPolizia, String codiceUfficio,
                                                            String nomeCognome, String ufficioOperatore, String ruoloOperatore, Pageable pageable);

    @Query(value = "SELECT COUNT(*) " +
            "FROM SSD_SECURITY.SEC_RICHIESTA_REGISTRAZIONE r JOIN SSD_SECURITY.SEC_STATO_RICHIESTA s ON r.STATO = s.ID_STATO " +
            "WHERE s.ID_STATO = ?1 " +
            "AND (?3 = 'R_UFFICIO_SICUREZZA' OR r.UFFICIO IN (SELECT * FROM JSON_TABLE(SSD_SECURITY.GET_UFF_GER(?2),'$[*]' COLUMNS VAL VARCHAR PATH '$')))", nativeQuery = true)
    Integer countRichiesteByStato(Integer stato, String ufficioOperatore, String ruoloOperatore);

    @Modifying
    @Query(value = "INSERT INTO SSD_SECURITY.SEC_LOG_CREAZIONE_UTENTE (IDPROT, AZIONE, ERRORE, DATA_INSERIMENTO) " +
            "values (?1, 'ERRORE CREAZIONE UTENZA', ?2, ?3)", nativeQuery = true)
    void scritturaErroreCreazione(String protocollo, String errore, LocalDate data);

    @Modifying
    @Query(value = "INSERT INTO SSD_SECURITY.SEC_TSI035_UTE_ORACLE(ID_UTE, N_TELE_UTE, C_FISC, COGN, NOME, SEX, D_NAS, C_LUO_NAS, DES_LUO_NAS, SGL_PRV_NAS, D_REG_INS, C_UTE_INS, C_UFF_INS, C_GRA, ID_T_UTE, IND_EMAIL) " +
            "values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16)", nativeQuery = true)
    void inserimentoTsiUtenteOracle(String codiceUtente, String numeroTelefono, String codiceFiscale, String cognome, String nome,
                                    Character sesso, LocalDate dataNascita, Integer luogoNascita, String descrizioneLuogoNascita,
                                    String siglaProvinciaNascita, String dataInserimento, String utenteInserimento,
                                    String ufficioInserimento, Integer qualifica,
                                    Integer tipoUtente, String email);

    @Query(value = "SELECT SSD_SECURITY.GET_UTE(?1, ?2) FROM DUAL", nativeQuery = true)
    String getCodiceUtenteCreazione(String codiceFiscale, Integer idForzaPolizia);

    @Query(value =
            "SELECT " +
            "   CASE u.PRIMARY_EMAIL " +
            "       WHEN 0 " +
            "           THEN u.EMAIL " +
            "       ELSE " +
            "           u.EMAIL_PRIV " +
            "   END EMAIL " +
            "FROM SSD_SECURITY.USERS u INNER JOIN SSD_SECURITY.SEC_COMANDANTE_UFFICI scu ON u.G_MEMBER = scu.UTE_CMD " +
            "WHERE scu.UFF_SEGN = ?1 " +
            "AND scu.DATA_CAN IS NULL " +
            "AND u.DATA_CAN IS NULL", nativeQuery = true)
    String getComandanteEmailByUfficio(String codiceUfficio);

}