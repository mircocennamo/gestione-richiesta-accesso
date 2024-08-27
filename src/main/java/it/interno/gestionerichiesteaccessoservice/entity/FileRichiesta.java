package it.interno.gestionerichiesteaccessoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Table(name = "SEC_FILE_RICHIESTA", schema = "SSD_SECURITY")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileRichiesta {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "APPLICATION_ID")
    private Integer applicationId;
    @Column(name = "NAME")
    private String nome;
    @Column(name = "FILENAME")
    private String filename;
    @Column(name = "MIME_TYPE")
    private String mimeType;
    @Column(name = "CREATED_ON")
    private Timestamp dataCreazione;

    @Lob
    @Column(name = "BLOB_CONTENT")
    private Blob blob;

    @ManyToOne
    @JoinColumn(name = "PROTOCOLLO", referencedColumnName = "IDPROT")
    private RichiestaRegistrazione richiestaRegistrazione;

}
