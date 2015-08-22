/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.data.entidad;

/**
 *
 * @author Alvaro
 */
public class Tramite {

    private String nombrePersona;
    private String documentoPersona;
    private String tipo;
    private String estado;
    private String usuarioResponsable;
    private String fecha;

    public Tramite() {

    }

    public Tramite(String nombrePersona, String documentoPersona, String tipo, String estado, String usuarioResponsable, String fecha) {
        this.nombrePersona = nombrePersona;
        this.documentoPersona = documentoPersona;
        this.tipo = tipo;
        this.estado = estado;
        this.usuarioResponsable = usuarioResponsable;
        this.fecha = fecha;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getDocumentoPersona() {
        return documentoPersona;
    }

    public void setDocumentoPersona(String documentoPersona) {
        this.documentoPersona = documentoPersona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
