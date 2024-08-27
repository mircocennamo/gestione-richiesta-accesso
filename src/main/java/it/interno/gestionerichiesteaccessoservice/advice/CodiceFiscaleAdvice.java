package it.interno.gestionerichiesteaccessoservice.advice;

import it.interno.gestionerichiesteaccessoservice.dto.ResponseDto;
import it.interno.gestionerichiesteaccessoservice.exceptions.CodiceFiscaleNonValidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mirco.cennamo on 27/08/2024
 * @project gestione-richiesteaccesso-service-master
 */
@ControllerAdvice
public class CodiceFiscaleAdvice {

    @ExceptionHandler(CodiceFiscaleNonValidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ResponseDto<String>> codiceFiscaleExceptionHandler(RuntimeException e) {
        ResponseDto<String> responseDto = ResponseDto.<String>builder().code(HttpStatus.UNPROCESSABLE_ENTITY.value()).error(e.getMessage()).build();
        return new ResponseEntity(responseDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
