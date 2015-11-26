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
import co.edu.uniandes.ecos.statusquo.operador.util.DTOUtil;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.UsuarioDTO;
import co.edu.uniandes.ecos.statusquo.operador.ws.service.UsuarioService;
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

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class UsuarioEJB implements Serializable {

    @EJB
    private AutenticacionDAO autenticacionDao;

    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private TipoUsuarioDAO tipoUsuarioDao;

    @EJB
    private SeguridadEJB seguridadEJB;

    @EJB
    private UsuarioService usuarioWS;

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
        List<Object> parametros = new ArrayList<>();
        String q = "Usuario.findByDocumentoExacto";
        parametros.add(documento);
        return usuarioDao.buscarNamedQuery(q, parametros);
    }

    public UsuarioDTO buscarUsuario(final String documento) throws Exception {
        List<Object> parametros = new ArrayList<>();
        String q = "Usuario.findByDocumentoExacto";
        parametros.add(documento);
        Usuario usuario = usuarioDao.buscarNamedQuery(q, parametros);
        UsuarioDTO usuarioDto;
        if (usuario == null) {
            usuarioDto = usuarioWS.obtenerUsuario(documento);
        } else {
            usuarioDto = DTOUtil.getUsuarioDTO(usuario);
            usuarioDto.setUsuarioLocal(true);
        }
        return usuarioDto;
    }

    public void transpasarUsuario(final String documento) throws Exception {
        UsuarioDTO usuarioDto = usuarioWS.obtenerUsuario(documento);
        if (usuarioDto != null) {
            List<Object> parametros = new ArrayList<>();
            String q = "Usuario.findByDocumentoExacto";
            parametros.add(documento);
            if (usuarioDao.buscarNamedQuery(q, parametros) == null) {
                Usuario usuarioSeleccionado = new Usuario();
                usuarioSeleccionado.setApellido1(usuarioDto.getApellido1());
                usuarioSeleccionado.setApellido2(usuarioDto.getApellido2());
                usuarioSeleccionado.setCorreo(usuarioDto.getCorreo());
                usuarioSeleccionado.setNombre1(usuarioDto.getNombre1());
                usuarioSeleccionado.setNombre2(usuarioDto.getNombre2());
                usuarioSeleccionado.setDocumento(usuarioDto.getDocumento());
                usuarioSeleccionado.setTelefono(usuarioDto.getTelefono());
                usuarioSeleccionado.setTipoDocumento(TipoDocumento.valueOf(usuarioDto.getTipoDocumento()));
                usuarioSeleccionado.setTipo(new TipoUsuario(2l));
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

    public void guardarUsuario(Usuario usuario) throws Exception {
        usuarioDao.insertar(usuario);

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

        usuarioWS.guardarUsuario(DTOUtil.getUsuarioDTO(usuario));

    }

}
