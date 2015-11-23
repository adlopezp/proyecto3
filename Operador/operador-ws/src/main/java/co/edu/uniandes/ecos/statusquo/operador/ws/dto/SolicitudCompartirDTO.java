package co.edu.uniandes.ecos.statusquo.operador.ws.dto;

import java.io.Serializable;

/**
 *
 * @author Alvaro
 *
 * DTO que se enviará como parámetro para enviar un mensaje con solicitud para
 * compartir un archivo
 */
public class SolicitudCompartirDTO implements Serializable {

    private String textoMensaje;
    private String identificacionSolicitante;
    private String identificacionDestinatario;
    private String nombreTipoArchivo;

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public String getNombreTipoArchivo() {
        return nombreTipoArchivo;
    }

    public void setNombreTipoArchivo(String nombreTipoArchivo) {
        this.nombreTipoArchivo = nombreTipoArchivo;
    }

    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }

    public String getIdentificacionSolicitante() {
        return identificacionSolicitante;
    }

    public void setIdentificacionSolicitante(String identificacionSolicitante) {
        this.identificacionSolicitante = identificacionSolicitante;
    }

}
