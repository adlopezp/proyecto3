/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.tipodocumentows;

import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.TipoDocumento;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ContextoRespuestaTipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev
 */
public class RespuestaGetTipoDocumentoWS {
    private ContextoRespuestaTipo respuestaEstandar=new ContextoRespuestaTipo();
    private List<TipoDocumento> tipoDocumento= new ArrayList<TipoDocumento>();

    public ContextoRespuestaTipo getRespuestaEstandar() {
        return respuestaEstandar;
    }

    public void setRespuestaEstandar(ContextoRespuestaTipo respuestaEstandar) {
        this.respuestaEstandar = respuestaEstandar;
    }

    public List<TipoDocumento> getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(List<TipoDocumento> tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    
}
