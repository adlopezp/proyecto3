package co.edu.uniandes.ecos.statusquo.operador.ws.service;

import co.edu.uniandes.ecos.statusquo.operador.ejb.PropertiesEJB;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.ContextoRespuestaTipo;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.NotificacionCompartidoDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.NotificacionCompartidoWS;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.NotificacionCompartidoWS_Service;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.SolicitudCompartirDTO;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class NotificacionService implements Serializable {

    @EJB
    private PropertiesEJB propertiesEJB;

    private NotificacionCompartidoWS_Service getService(final String url) {
        try {
            NotificacionCompartidoWS_Service service = new NotificacionCompartidoWS_Service(new URL(url + propertiesEJB.getProperty(PropertiesEJB.NOTIFICACION_WSDL_SERVICE_KEY)),
                    new QName("http://servicios.ws.operador.statusquo.ecos.uniandes.edu.co/", "NotificacionCompartidoWS"));
            return service;
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public void notificarCompartido(final NotificacionCompartidoDTO notificacion, final String url) {

        try {
            NotificacionCompartidoWS_Service service = getService(url);
            NotificacionCompartidoWS port = service.getNotificacionCompartidoWSPort();

            List<NotificacionCompartidoDTO> notificaciones = new ArrayList<>();
            notificaciones.add(notificacion);

            ContextoRespuestaTipo result = port.recepcionNotificaciones(notificaciones);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void solicitarCompartido(final SolicitudCompartirDTO solicitud, final String url) {
        try {
            NotificacionCompartidoWS_Service service = getService(url);
            NotificacionCompartidoWS port = service.getNotificacionCompartidoWSPort();

            List<SolicitudCompartirDTO> solicitudes = new ArrayList<>();
            solicitudes.add(solicitud);

            ContextoRespuestaTipo result = port.recepcionSolicitudes(solicitudes);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

    }
}
