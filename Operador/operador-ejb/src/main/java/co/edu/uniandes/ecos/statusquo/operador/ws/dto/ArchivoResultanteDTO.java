/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alvaro
 *
 * DTO que se enviará como parametro con el archivo resultante de un tramite.
 */
public class ArchivoResultanteDTO implements Serializable {

    private byte[] archivo;
    private String nombreArchivo;
    private String size;
    private Date fecha;
    private String identificacionDestinatario;
    private String identificacionRemitente;
    private String nombreRemitente;
    private String nombreTipoArchivo;
    private String formato;
    private String tramite;
    private String detalles;

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdentificacionRemitente() {
        return identificacionRemitente;
    }

    public void setIdentificacionRemitente(String identificacionRemitente) {
        this.identificacionRemitente = identificacionRemitente;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
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

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }
}
