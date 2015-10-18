/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios;

import co.edu.uniandes.ecos.statusquo.centralizador.ejb.TipoDocumentoBean;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.FalloTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.Servicio;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.tipodocumentows.RespuestaGetTipoDocumentoWS;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@WebService(serviceName = "TipoDocumentoSW")
@Stateless()
public class TipoDocumentoSW extends Servicio {
    
    @EJB
    private TipoDocumentoBean tipoDocumentoEJB;

    @WebMethod(operationName = "getTipoDocumentos")
    public RespuestaGetTipoDocumentoWS getTipoDocumentos() throws FalloTipo {
        RespuestaGetTipoDocumentoWS resp = new RespuestaGetTipoDocumentoWS();
        try {
            resp.setTipoDocumento(tipoDocumentoEJB.consultarTipoDocumento());
            //resp.getRespuestaEstandar().setIdTx(null); TODO agregar id del modulo de seguridad encargado del registro de movimientos
            resp.getRespuestaEstandar().setCodEstadoTx("1");
            resp.getRespuestaEstandar().setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al consultar la lista de tipo documento", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.getRespuestaEstandar().setCodEstadoTx("0");
            throw new FalloTipo(resp.getRespuestaEstandar(), errores);
        }
        return resp;
    }
}
