package co.edu.uniandes.ecos.statusquo.operador.ws.service;

import co.edu.uniandes.ecos.statusquo.operador.ejb.PropertiesEJB;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.OperadorDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.UsuarioDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.ContextoRespuestaTipo;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.Operador;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.RespuestaGetDocumentoUsuarioWS;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.UsuarioSW;
import co.edu.uniandes.ecos.statusquo.operador.ws.imp.UsuarioSW_Service;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.PostConstruct;
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
public class UsuarioService implements Serializable {

    @EJB
    private PropertiesEJB propertiesEJB;

//    @WebServiceRef(wsdlLocation = "http://localhost:8080/centralizador-ws/UsuarioSW?wsdl")
//    @HandlerChain(file = "/LogHandler.xml")
    private UsuarioSW_Service service;

    @PostConstruct
    public void init() {
        try {
            service = new UsuarioSW_Service(new URL(propertiesEJB.getProperty(PropertiesEJB.CENTRALIZADOR_WSDL_KEY)),
                    new QName("http://servicios.ws.centralizador.statusquo.ecos.uniandes.edu.co/", "UsuarioSW"));
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public UsuarioDTO obtenerUsuario(final String identificacion) throws Exception {
        try { // Call Web Service Operation
            UsuarioSW port = service.getUsuarioSWPort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            RespuestaGetDocumentoUsuarioWS result = port.getUsuario(identificacion);
            System.out.println("Result = " + result);
            if (result.getUsuario() != null) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(result.getUsuario().getId() == null ? null : result.getUsuario().getId().longValue());
                usuario.setApellido1(result.getUsuario().getApellido1());
                usuario.setApellido2(result.getUsuario().getApellido2());
                usuario.setCorreo(result.getUsuario().getCorreo());
                usuario.setNombre1(result.getUsuario().getNombre1());
                usuario.setNombre2(result.getUsuario().getNombre2());
                usuario.setDocumento(result.getUsuario().getNumeroIdentificacion());
                usuario.setTelefono(result.getUsuario().getTelefono());
                usuario.setTipoDocumento(result.getUsuario().getTipoIdentificacion());

                OperadorDTO operador = new OperadorDTO();

                operador.setDescripcion(result.getUsuario().getOperador().getDescripcion());
                operador.setId(result.getUsuario().getOperador().getId() == null ? null : result.getUsuario().getOperador().getId().longValue());
                operador.setNombre(result.getUsuario().getOperador().getNombre());
                operador.setUrl(result.getUsuario().getOperador().getUrl());
                usuario.setOperador(operador);

                return usuario;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void guardarUsuario(UsuarioDTO usuarioDto) throws Exception {
        try { // Call Web Service Operation
            UsuarioSW port = service.getUsuarioSWPort();
            // TODO initialize WS operation arguments here
            Usuario usuario = new Usuario();
            usuario.setApellido1(usuarioDto.getApellido1());
            usuario.setApellido2(usuarioDto.getApellido2());
            usuario.setCorreo(usuarioDto.getCorreo());
            usuario.setNombre1(usuarioDto.getNombre1());
            usuario.setNombre2(usuarioDto.getNombre2());
            usuario.setNumeroIdentificacion(usuarioDto.getDocumento());
            // Datos Operador
            Operador ope = new Operador();
            ope.setNombre(propertiesEJB.getProperty("operador.nombre"));
            ope.setUrl(propertiesEJB.getProperty("operador.url"));
            ope.setDescripcion(propertiesEJB.getProperty("operador.descripcion"));

            usuario.setOperador(ope);
            usuario.setTelefono(usuarioDto.getTelefono());
            usuario.setTipoIdentificacion(usuarioDto.getTipoDocumento());

            ContextoRespuestaTipo result = port.setUsuario(usuario);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

}
