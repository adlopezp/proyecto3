package co.edu.uniandes.ecos.statusquo.operador.ws.service;

import co.edu.uniandes.ecos.statusquo.operador.ejb.PropertiesEJB;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.ArchivoResultanteDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.ContextoRespuestaTipo;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.NotificacionEntidadWS;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.NotificacionEntidadWS_Service;
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
public class EntidadService implements Serializable {

    @EJB
    private PropertiesEJB propertiesEJB;

    private NotificacionEntidadWS_Service getService(final String url) {
        try {
            NotificacionEntidadWS_Service service = new NotificacionEntidadWS_Service(new URL(url + propertiesEJB.getProperty(PropertiesEJB.ENTIDAD_WSDL_SERVICE_KEY)),
                    new QName("http://servicios.ws.operador.statusquo.ecos.uniandes.edu.co/", "NotificacionEntidadWS"));
            return service;
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public void notificarCompartido(final ArchivoResultanteDTO archivo, final String url) {

        try {
            NotificacionEntidadWS_Service service = getService(url);
            NotificacionEntidadWS port = service.getNotificacionEntidadWSPort();
            // TODO initialize WS operation arguments here
            List<ArchivoResultanteDTO> archivos = new ArrayList<>();
            archivos.add(archivo);
            // TODO process result here
            ContextoRespuestaTipo result = port.recepcionNotificacionEntidad(archivos);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }

}
