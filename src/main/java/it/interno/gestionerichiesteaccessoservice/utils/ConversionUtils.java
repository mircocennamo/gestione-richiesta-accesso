package it.interno.gestionerichiesteaccessoservice.utils;

import it.interno.gestionerichiesteaccessoservice.entity.Qualifica;
import it.interno.gestionerichiesteaccessoservice.exceptions.BlobConversionException;
import org.apache.commons.lang3.StringUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public interface ConversionUtils {

    static String convertBlobToString(Blob immagine){

        if(immagine == null)
            return null;

        InputStream is = null;
        ByteArrayOutputStream os = null;

        try{
            is = immagine.getBinaryStream();
            os = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int byteRead = -1;

            while((byteRead = is.read(buffer)) != -1){
                os.write(buffer, 0, byteRead);
            }

        } catch (SQLException | IOException e) {
            throw new BlobConversionException("Si è verificato un errore nella conversione del BLOB");
        }

        byte[] byteArray = os.toByteArray();
        return new String(byteArray, StandardCharsets.UTF_8);
    }

    static SerialBlob convertStringToBlob(String immagine){

        if(StringUtils.isBlank(immagine))
            return null;

        SerialBlob sb = null;

        try {
            sb = new SerialBlob(immagine.getBytes());
        } catch (SQLException e) {
            throw new BlobConversionException("Si è verificato un errore nella conversione del BLOB");
        }

        return sb;
    }

    static Timestamp getCurrentTimestamp(){
        ZoneId fusoOrario = ZoneId.of("Europe/Rome");
        return Timestamp.valueOf(LocalDateTime.now(fusoOrario));
    }

    static String getDescrizioneQualifica(Qualifica qualifica, Integer idEnte){
        if(idEnte == 1)
            return qualifica.getPolizia();
        if(idEnte == 2)
            return qualifica.getCarabinieri();
        if(idEnte == 3)
            return qualifica.getGuardiaDiFinanza();
        if(idEnte == 4)
            return qualifica.getPoliziaPenitenziaria();

        else return qualifica.getPersonaleCivile();
    }
}
