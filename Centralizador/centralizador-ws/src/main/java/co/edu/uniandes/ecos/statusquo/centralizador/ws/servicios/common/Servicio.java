/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios.common;

import co.edu.uniandes.ecos.statusquo.centralizador.ws.service.respuesta.ErrorTipo;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.Resource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceContext;

/**
 *
 * @author Dev
 */
public class Servicio {

    protected List<ErrorTipo> errores = new ArrayList<ErrorTipo>();
    protected String stringUsuario;
    protected String stringContrasena;
    @Resource
    protected WebServiceContext ctx;

    protected void controlFallo(String codError, String valDescError, String valDescTecnico) {
        ErrorTipo error = new ErrorTipo();
        error.setCodError(codError);
        error.setValDescError(valDescError);
        error.setValDescErrorTecnico(valDescTecnico);
        errores.add(error);
    }

    protected XMLGregorianCalendar getFecha() {
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(new Date());
        XMLGregorianCalendar xgcal=null;
        try {
            xgcal = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gcal);       
        } catch (Exception ex) {
            controlFallo("500", "Fallo al asignar fecha del sistema", ex.getMessage());
        }
        return xgcal;
    }
}
