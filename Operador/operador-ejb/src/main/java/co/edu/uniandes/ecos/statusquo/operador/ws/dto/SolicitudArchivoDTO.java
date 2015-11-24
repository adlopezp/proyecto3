package co.edu.uniandes.ecos.statusquo.operador.ws.dto;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 *
 * DTO que se enviará como parámetro para solicitar un archivo compartido
 */
public class SolicitudArchivoDTO implements Serializable {

    private String idArchivo;
    private String identificacionSolicitante;
    private String identificacionDestinatario;

    public void setIdArchivo(String idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getIdArchivo() {
        return idArchivo;
    }

    public String getIdentificacionSolicitante() {
        return identificacionSolicitante;
    }

    public void setIdentificacionSolicitante(String identificacionSolicitante) {
        this.identificacionSolicitante = identificacionSolicitante;
    }

    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }

}
