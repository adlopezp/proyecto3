/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios;

import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.FalloTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.Servicio;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.NotificacionCompartidoDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.SolicitudCompartirDTO;
import contextorespuestatipo.servicio.ws.operador.statusquo.ecos.uniandes.edu.co.ContextoRespuestaTipo;
import errortipo.servicio.ws.operador.statusquo.ecos.uniandes.edu.co.ErrorTipo;
import java.util.ArrayList;
import java.util.List;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Dev
 */
@WebService(serviceName = "NotificacionCompartidoWS")
@HandlerChain(file = "/LogHandler.xml")
public class NotificacionCompartidoWS extends Servicio {

    @WebMethod(operationName = "recepcionNotificaciones")
    public ContextoRespuestaTipo recepcionNotificaciones(@WebParam(name = "notificacion") List<NotificacionCompartidoDTO> notificacion) throws FalloTipo {
        errores = new ArrayList<ErrorTipo>();
        ContextoRespuestaTipo resp = new ContextoRespuestaTipo();
        try {
            //TODO logica de notificacion
            resp.setCodEstadoTx("1");
            resp.setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al notificar al usuario usuario", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.setCodEstadoTx("0");
            throw new FalloTipo(resp, errores);
        }
        return resp;
    }
    
    @WebMethod(operationName = "recepcionSolicitudes")
    public ContextoRespuestaTipo recepcionSolicitudes(@WebParam(name = "solicitud") List<SolicitudCompartirDTO> solicitud) throws FalloTipo {
        errores = new ArrayList<ErrorTipo>();
        ContextoRespuestaTipo resp = new ContextoRespuestaTipo();
        try {
            //TODO logica de solicitud
            resp.setCodEstadoTx("1");
            resp.setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al solicitar documentos al usuario", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.setCodEstadoTx("0");
            throw new FalloTipo(resp, errores);
        }
        return resp;
    }
}
