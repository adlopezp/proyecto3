/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios.documento;

import co.edu.uniandes.ecos.statusquo.operador.ws.dto.ArchivoResultanteDTO;
import contextorespuestatipo.servicio.ws.operador.statusquo.ecos.uniandes.edu.co.ContextoRespuestaTipo;
import java.io.Serializable;

/**
 *
 * @author Dev
 */
public class RespuestaDocumento implements Serializable {

    private ArchivoResultanteDTO archivo = new ArchivoResultanteDTO();
    private ContextoRespuestaTipo respuestaEstandar = new ContextoRespuestaTipo();

    public ArchivoResultanteDTO getArchivo() {
        return archivo;
    }

    public void setArchivo(final ArchivoResultanteDTO archivo) {
        this.archivo = archivo;
    }

    public ContextoRespuestaTipo getRespuestaEstandar() {
        return respuestaEstandar;
    }

    public void setRespuestaEstandar(ContextoRespuestaTipo respuestaEstandar) {
        this.respuestaEstandar = respuestaEstandar;
    }
}
