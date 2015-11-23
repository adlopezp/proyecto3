/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios;

import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.FalloTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.Servicio;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.ArchivoResultanteDTO;
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
@WebService(serviceName = "NotificacionEntidadWS")
@HandlerChain(file = "/LogHandler.xml")
public class NotificacionEntidadWS extends Servicio{

    @WebMethod(operationName = "recepcionNotificacionEntidad")
    public ContextoRespuestaTipo recepcionNotificacionEntidad(@WebParam(name = "envio") List<ArchivoResultanteDTO> envio) throws FalloTipo {
        errores = new ArrayList<ErrorTipo>();
        ContextoRespuestaTipo resp = new ContextoRespuestaTipo();
        try {
            //TODO logica de envio
            resp.setCodEstadoTx("1");
            resp.setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al recibir el documento de la entidad", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.setCodEstadoTx("0");
            throw new FalloTipo(resp, errores);
        }
        return resp;
    }
}
