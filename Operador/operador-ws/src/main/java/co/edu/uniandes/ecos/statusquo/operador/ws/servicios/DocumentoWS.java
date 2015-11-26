/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios;

import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.FalloTipo;
import co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common.Servicio;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.SolicitudArchivoDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.servicios.documento.RespuestaDocumento;
import java.util.ArrayList;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Alvaro
 */
@WebService(serviceName = "DocumentoWS")
@HandlerChain(file = "/LogHandler.xml")
public class DocumentoWS extends Servicio {

    @WebMethod(operationName = "getDocumento")
    public RespuestaDocumento getDocumento(@WebParam(name = "solicitudArchivo") SolicitudArchivoDTO solicitudArchivo) throws FalloTipo {
        errores = new ArrayList<>();
        RespuestaDocumento resp = new RespuestaDocumento();
        try {
            //TODO logica de documento
            resp.getRespuestaEstandar().setCodEstadoTx("1");
            resp.getRespuestaEstandar().setFechaTx(getFecha());
        } catch (Exception ex) {
            controlFallo("500", "Error al enviar el documento", ex.getMessage());
        }
        if (!errores.isEmpty()) {
            resp.getRespuestaEstandar().setCodEstadoTx("0");
            throw new FalloTipo(resp.getRespuestaEstandar(), errores);
        }
        return resp;
    }
}
