/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common;

import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ContextoRespuestaTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ErrorTipo;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.WebFault;

/**
 *
 * @author Dev
 */
@WebFault(name = "FalloTipo", targetNamespace = "http://co.edu.uniandes.ecos.statusquo.centralizador.ws.servicio.exception/")
public class FalloTipo
    extends Exception
{

    private ContextoRespuestaTipo contextoRespuesta;
    private List<ErrorTipo> error;

    public ContextoRespuestaTipo getContextoRespuesta() {
        return contextoRespuesta;
    }

    public List<ErrorTipo> getError() {
        if (error == null) {
            error = new ArrayList<ErrorTipo>();
        }
        return this.error;
    }


    public FalloTipo(ContextoRespuestaTipo contextoRespuesta,List<ErrorTipo> error) {
        this.contextoRespuesta=contextoRespuesta;
        this.error=error;
    }

    public FalloTipo(ContextoRespuestaTipo contextoRespuesta,List<ErrorTipo> error,Throwable cause) {
        this.contextoRespuesta=contextoRespuesta;
        this.error=error;
    }
}
