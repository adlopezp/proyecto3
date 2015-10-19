package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.AutenticacionDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.UsuarioDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Autenticacion;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class UsuarioEJB {

    @EJB
    AutenticacionDAO autenticacionDao;

    @EJB
    UsuarioDAO usuarioDao;

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
        } else {
            if (documento == null || documento.isEmpty()) {
                q = "Usuario.findByNombre";
                parametros.add("%" + nombre + "%");
            } else {
                q = "Usuario.findByNombreDocumento";
                parametros.add("%" + nombre + "%");
                parametros.add(documento);
            }
        }
        return usuarioDao.consultarNamedQuery(q, parametros);
    }

    public void guardarAutenticacion(final Autenticacion autenticacion) {
        autenticacionDao.insertar(autenticacion);
    }

    public void guardarUsuario(final Usuario usuario) {
        usuarioDao.insertar(usuario);
    }

    public void actualizarAutenticacion(final Autenticacion autenticacion) {
        autenticacionDao.actualizar(autenticacion);
    }

    public void actualizarUsuario(final Usuario usuario) {
        usuarioDao.actualizar(usuario);
    }
}
