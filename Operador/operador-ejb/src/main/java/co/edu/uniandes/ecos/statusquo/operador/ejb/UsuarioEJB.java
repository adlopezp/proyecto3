package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.AutenticacionDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.TipoUsuarioDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.UsuarioDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Autenticacion;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.CarpetaPersonal;
import co.edu.uniandes.ecos.statusquo.operador.entity.EstadoCarpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.TipoCarpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.TipoDocumento;
import co.edu.uniandes.ecos.statusquo.operador.entity.TipoUsuario;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class UsuarioEJB implements Serializable {

    @EJB
    AutenticacionDAO autenticacionDao;

    @EJB
    UsuarioDAO usuarioDao;

    @EJB
    TipoUsuarioDAO tipoUsuarioDao;

    @EJB
    private SeguridadEJB seguridadEJB;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/centralizador-ws/UsuarioSW?wsdl")
    @HandlerChain(file = "/LogHandler.xml")
    private UsuarioSW_Service service;

    public Usuario auntenticar(final String codigo, final String password) {
        Usuario usuario = null;
        List<Object> parametros = new ArrayList<>();
        parametros.add(codigo);
        parametros.add(password);
        Autenticacion aut = autenticacionDao.buscarNamedQuery("Autenticacion.autenticar", parametros);
        if (aut != null) {
            usuario = aut.getUsuario();
        }

        return usuario;
    }

    public List<Usuario> buscarUsuarios(final String nombre, final String documento) {
        List<Object> parametros = new ArrayList<>();
        String q;
        if (nombre == null || nombre.isEmpty()) {
            if (documento == null || documento.isEmpty()) {
                q = "Usuario.findAll";
            } else {
                q = "Usuario.findByDocumento";
                parametros.add(documento);
            }
        } else if (documento == null || documento.isEmpty()) {
            q = "Usuario.findByNombre";
            parametros.add("%" + nombre + "%");
        } else {
            q = "Usuario.findByNombreDocumento";
            parametros.add("%" + nombre + "%");
            parametros.add(documento);
        }
        return usuarioDao.consultarNamedQuery(q, parametros);
    }

    public Usuario obtenerUsuario(final String documento) {
        try { // Call Web Service Operation
            UsuarioSW port = service.getUsuarioSWPort();
            // TODO initialize WS operation arguments here
            String identificacion = documento;
            // TODO process result here
            RespuestaGetDocumentoUsuarioWS result = port.getUsuario(identificacion);
            System.out.println("Result = " + result);
            if (result.getUsuario() != null) {
                Usuario usuarioSeleccionado = new Usuario();
                usuarioSeleccionado.setApellido1(result.getUsuario().getApellido1());
                usuarioSeleccionado.setApellido2(result.getUsuario().getApellido2());
                usuarioSeleccionado.setCorreo(result.getUsuario().getCorreo());
                usuarioSeleccionado.setNombre1(result.getUsuario().getNombre1());
                usuarioSeleccionado.setNombre2(result.getUsuario().getNombre2());
                usuarioSeleccionado.setDocumento(result.getUsuario().getNumeroIdentificacion());
                usuarioSeleccionado.setTelefono(result.getUsuario().getTelefono());
                usuarioSeleccionado.setTipoDocumento(TipoDocumento.valueOf(result.getUsuario().getTipoIdentificacion()));
                usuarioSeleccionado.setTipo(new TipoUsuario(new Long(2)));
                return usuarioSeleccionado;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        List<Object> parametros = new ArrayList<>();
        String q = "Usuario.findByDocumentoExacto";
        parametros.add(documento);
        return usuarioDao.buscarNamedQuery(q, parametros);
    }

    public void transpasarUsuario(final String documento) {
        try { // Call Web Service Operation
            UsuarioSW port = service.getUsuarioSWPort();
            // TODO initialize WS operation arguments here
            String identificacion = documento;
            // TODO process result here
            RespuestaGetDocumentoUsuarioWS result = port.getUsuario(identificacion);
            System.out.println("Result = " + result);
            if (result.getUsuario() != null) {
                List<Object> parametros = new ArrayList<>();
                String q = "Usuario.findByDocumentoExacto";
                parametros.add(documento);
                if (usuarioDao.buscarNamedQuery(q, parametros) == null) {
                    Usuario usuarioSeleccionado = new Usuario();
                    usuarioSeleccionado.setApellido1(result.getUsuario().getApellido1());
                    usuarioSeleccionado.setApellido2(result.getUsuario().getApellido2());
                    usuarioSeleccionado.setCorreo(result.getUsuario().getCorreo());
                    usuarioSeleccionado.setNombre1(result.getUsuario().getNombre1());
                    usuarioSeleccionado.setNombre2(result.getUsuario().getNombre2());
                    usuarioSeleccionado.setDocumento(result.getUsuario().getNumeroIdentificacion());
                    usuarioSeleccionado.setTelefono(result.getUsuario().getTelefono());
                    usuarioSeleccionado.setTipoDocumento(TipoDocumento.valueOf(result.getUsuario().getTipoIdentificacion()));
                    usuarioSeleccionado.setTipo(new TipoUsuario(new Long(2)));
                    Autenticacion aut = new Autenticacion();
                    aut.setCodigo(usuarioSeleccionado.getNombre1() + "." + usuarioSeleccionado.getApellido1());
                    aut.setPassword("ecos2015");
                    aut.setUsuario(usuarioSeleccionado);
                    usuarioSeleccionado.setAutenticacion(aut);
                    guardarUsuario(usuarioSeleccionado);
                    guardarAutenticacion(aut);
                    actualizarUsuario(usuarioSeleccionado);
                }
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    }

    public Autenticacion obtenerAutenticacion(final String login) {
        List<Object> parametros = new ArrayList<>();
        String q = "Autenticacion.findByLogin";
        parametros.add(login);
        Autenticacion objeto = autenticacionDao.buscarNamedQuery(q, parametros);
        if (objeto != null) {
            autenticacionDao.desconectar(objeto);
            objeto.setPassword(null);
        }
        return objeto;
    }

    public void actualizarAutenticacion(final Autenticacion autenticacion) {
        autenticacionDao.actualizar(autenticacion);
    }

    public void actualizarUsuario(final Usuario usuario) {
        usuarioDao.actualizar(usuario);
    }

    public List<TipoUsuario> getTiposUsuario() {
        return tipoUsuarioDao.consultar();
    }

    public void guardarAutenticacion(final Autenticacion autenticacion) {
        autenticacionDao.insertar(autenticacion);
    }

    public void guardarUsuario(Usuario usuario) {
        usuarioDao.insertar(usuario);

        if (usuario.getAutenticacion() == null) {
            try { // Call Web Service Operation
                UsuarioSW port = service.getUsuarioSWPort();
                // TODO initialize WS operation arguments here
                co.edu.uniandes.ecos.statusquo.operador.ejb.Usuario usuariocen = new co.edu.uniandes.ecos.statusquo.operador.ejb.Usuario();
                usuariocen.setApellido1(usuario.getApellido1());
                usuariocen.setApellido2(usuario.getApellido2());
                usuariocen.setCorreo(usuario.getCorreo());
                usuariocen.setNombre1(usuario.getNombre1());
                usuariocen.setNombre2(usuario.getNombre2());
                usuariocen.setNumeroIdentificacion(usuario.getDocumento());
                Operador ope = new Operador();
                ope.setNombre("Operador1");
                ope.setUrl("http://localhost:8080/operador-ws/");
                ope.setDescripcion("Operador1");
                usuariocen.setOperador(ope);
                usuariocen.setTelefono(usuario.getTelefono());
                usuariocen.setTipoIdentificacion(usuario.getTipoDocumento().toString());
                ContextoRespuestaTipo result = port.setUsuario(usuariocen);
                System.out.println("Result = " + result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        }
        //Creando carpetas
        CarpetaPersonal carpetaPersonal = new CarpetaPersonal();
        carpetaPersonal.setUsuario(usuario);

        usuario.setCarpetaPersonal(carpetaPersonal);

        Carpeta inbox = new Carpeta();
        inbox.setPrincipal(true);
        inbox.setCarpetaPersonal(carpetaPersonal);
        inbox.setEstado(new EstadoCarpeta(1L)); //Activa
        inbox.setNombre("Carpeta Personal");
        inbox.setTipo(new TipoCarpeta(1L));

        Carpeta papelera = new Carpeta();
        papelera.setPrincipal(true);
        papelera.setCarpetaPersonal(carpetaPersonal);
        papelera.setEstado(new EstadoCarpeta(1L)); //Activa
        papelera.setNombre("Papelera");
        papelera.setTipo(new TipoCarpeta(1L));

        List<Carpeta> carpetas = new ArrayList<>();
        carpetas.add(inbox);
        carpetas.add(papelera);

        carpetaPersonal.setCarpetaList(carpetas);

        usuarioDao.actualizar(usuario);

        try {
            seguridadEJB.escribirLlaves(usuario);
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(UsuarioEJB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
