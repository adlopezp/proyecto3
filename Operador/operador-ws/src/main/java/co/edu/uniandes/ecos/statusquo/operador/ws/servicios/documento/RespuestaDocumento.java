/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios.documento;

import co.edu.uniandes.ecos.statusquo.operador.ws.dto.ArchivoResultanteDTO;
import contextorespuestatipo.servicio.ws.operador.statusquo.ecos.uniandes.edu.co.ContextoRespuestaTipo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev
 */
public class RespuestaDocumento implements Serializable{
    
    private List<ArchivoResultanteDTO> solicitudArchivo= new ArrayList<ArchivoResultanteDTO>();
    private ContextoRespuestaTipo respuestaEstandar=new ContextoRespuestaTipo();

    public List<ArchivoResultanteDTO> getSolicitudArchivo() {
        return solicitudArchivo;
    }

    public void setSolicitudArchivo(List<ArchivoResultanteDTO> solicitudArchivo) {
        this.solicitudArchivo = solicitudArchivo;
    }

    public ContextoRespuestaTipo getRespuestaEstandar() {
        return respuestaEstandar;
    }

    public void setRespuestaEstandar(ContextoRespuestaTipo respuestaEstandar) {
        this.respuestaEstandar = respuestaEstandar;
    }
}
