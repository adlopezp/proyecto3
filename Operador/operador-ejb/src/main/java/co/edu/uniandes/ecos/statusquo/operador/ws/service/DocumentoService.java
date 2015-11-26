package co.edu.uniandes.ecos.statusquo.operador.ws.service;

import co.edu.uniandes.ecos.statusquo.operador.ejb.PropertiesEJB;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.ArchivoResultanteDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.DocumentoWS;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.DocumentoWS_Service;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.RespuestaDocumento;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.SolicitudArchivoDTO;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class DocumentoService implements Serializable {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/operador-ws/DocumentoWS?wsdl")
    @HandlerChain(file = "/LogHandler.xml")
    private DocumentoWS_Service service2;

    @EJB
    private PropertiesEJB propertiesEJB;

    // Probar ambos metodos
    private DocumentoWS getPort(final String url) {
        DocumentoWS port = service2.getDocumentoWSPort();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        return port;
    }

    private DocumentoWS_Service getService(final String url) {
        try {
            DocumentoWS_Service service = new DocumentoWS_Service(new URL(url + propertiesEJB.getProperty(PropertiesEJB.DOCUMENTO_WSDL_SERVICE_KEY)),
                    new QName("http://servicios.ws.operador.statusquo.ecos.uniandes.edu.co/", "DocumentoWS"));
            return service;
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public byte[] getDocumento(final String idArchivo, final String url) throws Exception {
        try {
            DocumentoWS_Service service = getService(url);
            DocumentoWS port = service.getDocumentoWSPort();

            SolicitudArchivoDTO solicitudArchivo = new SolicitudArchivoDTO();

            RespuestaDocumento result = port.getDocumento(solicitudArchivo);
            System.out.println("Result = " + result);

            ArchivoResultanteDTO respuesta = result.getArchivo();
            return respuesta.getArchivo();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
