/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.usuariows;

import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ContextoRespuestaTipo;
import java.io.Serializable;

/**
 *
 * @author Dev
 */
public class RespuestaGetDocumentoUsuarioWS implements Serializable {
    
    private ContextoRespuestaTipo respuestaEstandar=new ContextoRespuestaTipo();
    private Usuario usuario= new Usuario();

    public ContextoRespuestaTipo getRespuestaEstandar() {
        return respuestaEstandar;
    }

    public void setRespuestaEstandar(ContextoRespuestaTipo respuestaEstandar) {
        this.respuestaEstandar = respuestaEstandar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
