/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.entidadws;

import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Entidad;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ContextoRespuestaTipo;

/**
 *
 * @author Dev
 */
public class RespuestaGetEntidadWS {
    private ContextoRespuestaTipo respuestaEstandar=new ContextoRespuestaTipo();
    private Entidad entidad= new Entidad();

    public ContextoRespuestaTipo getRespuestaEstandar() {
        return respuestaEstandar;
    }

    public void setRespuestaEstandar(ContextoRespuestaTipo respuestaEstandar) {
        this.respuestaEstandar = respuestaEstandar;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    
}
