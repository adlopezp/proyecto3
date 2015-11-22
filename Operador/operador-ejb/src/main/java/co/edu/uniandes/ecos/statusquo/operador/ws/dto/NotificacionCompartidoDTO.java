package co.edu.uniandes.ecos.statusquo.operador.ws.dto;

/**
 *
 * @author Alvaro
 *
 * DTO que se enviará como parámetro para enviar un mensaje con la notificacion
 * de que un archivo fue compartido con un usuario;
 */
public class NotificacionCompartidoDTO {

    private String textoMensaje;
    private String identificacionDestinatario;
    private String identificacionRemitente;
    private String idArchivo;
    private String nombreArchivo;
    private String sizeArchivo;
    private String nombreTipoArchivo;
    private String formato;

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }

    public String getIdentificacionRemitente() {
        return identificacionRemitente;
    }

    public void setIdentificacionRemitente(String identificacionRemitente) {
        this.identificacionRemitente = identificacionRemitente;
    }

    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }

    public String getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(String idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getSizeArchivo() {
        return sizeArchivo;
    }

    public void setSizeArchivo(String sizeArchivo) {
        this.sizeArchivo = sizeArchivo;
    }

    public String getNombreTipoArchivo() {
        return nombreTipoArchivo;
    }

    public void setNombreTipoArchivo(String nombreTipoArchivo) {
        this.nombreTipoArchivo = nombreTipoArchivo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

}
