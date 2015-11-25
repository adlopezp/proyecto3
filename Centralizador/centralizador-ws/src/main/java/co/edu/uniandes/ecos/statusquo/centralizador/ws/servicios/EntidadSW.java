/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios;

import co.edu.uniandes.ecos.statusquo.centralizador.ejb.EntidadBean;
import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Entidad;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ContextoRespuestaTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.FalloTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.Servicio;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.entidadws.RespuestaGetEntidadWS;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Dev
 */
@WebService(serviceName = "EntidadSW")
@HandlerChain(file="/LogServerHandler.xml")
public class EntidadSW extends Servicio {

@EJB
    private EntidadBean usuarioEJB;
    
    @WebMethod(operationName = "setEntidad")
    public ContextoRespuestaTipo setEntidad(@WebParam(name = "usuario") Entidad user) throws FalloTipo {
        ContextoRespuestaTipo resp = new ContextoRespuestaTipo();
        try {
            usuarioEJB.createEntidad(user);
            //resp.getRespuestaEstandar().setIdTx(null); TODO agregar id del modulo de seguridad encargado del registro de movimientos
            resp.setCodEstadoTx("1");
            resp.setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al crear el entidad", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.setCodEstadoTx("0");
            throw new FalloTipo(resp, errores);
        }
        return resp;
    }

    @WebMethod(operationName = "getEntidad")
    public RespuestaGetEntidadWS getEntidad(@WebParam(name = "razonSocial") String razonSocial) throws FalloTipo {
        RespuestaGetEntidadWS resp = new RespuestaGetEntidadWS();
        try {
            resp.setEntidad(usuarioEJB.consultarRazonSocial(razonSocial));
            //resp.getRespuestaEstandar().setIdTx(null); TODO agregar id del modulo de seguridad encargado del registro de movimientos
            resp.getRespuestaEstandar().setCodEstadoTx("1");
            resp.getRespuestaEstandar().setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al consultar el entidad", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.getRespuestaEstandar().setCodEstadoTx("0");
            throw new FalloTipo(resp.getRespuestaEstandar(), errores);
        }
        return resp;
    }
}
