/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean.usuario;

import co.edu.uniandes.ecos.statusquo.operador.ejb.UsuarioEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Autenticacion;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.util.Util;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alvaro
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    UsuarioEJB usuarioService;

    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;
    private String nombreBusqueda;
    private String documentoBusqueda;
    private String login;
    private String password;
    private String passwordNuevamente;
    private String archivo;

    @PostConstruct
    public void init() {
        usuarioSeleccionado = new Usuario();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void buscarUsuarios() {
        usuarios = usuarioService.buscarUsuarios(nombreBusqueda, documentoBusqueda);
    }

    public void guardarUsuario() {
        if (usuarioSeleccionado.getTipoDocumento() == null) {
            UtilBean.printMensajeErrorSimple("Tipo Documento Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getDocumento())) {
            UtilBean.printMensajeErrorSimple("Documento Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getNombre1())) {
            UtilBean.printMensajeErrorSimple("Primer Nombre Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getApellido1())) {
            UtilBean.printMensajeErrorSimple("Primer Apellido Obligatorio");
        }
        if (Util.isEmpty(password) || Util.isEmpty(passwordNuevamente)) {
            UtilBean.printMensajeErrorSimple("Contraseña Obligatoria");
        }
        if (!Util.isEmpty(password) && !Util.isEmpty(passwordNuevamente) && !password.equals(passwordNuevamente)) {
            UtilBean.printMensajeErrorSimple("Las contraseñas no coinciden");
        }
        if (Util.isEmpty(login)) {
            UtilBean.printMensajeErrorSimple("Usuario Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getCorreo())) {
            UtilBean.printMensajeErrorSimple("Correo Electrónico Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getTelefono())) {
            UtilBean.printMensajeErrorSimple("Telefono Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getCelular())) {
            UtilBean.printMensajeErrorSimple("Celular Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getDireccion())) {
            UtilBean.printMensajeErrorSimple("Celular Obligatorio");
        }

        if (!UtilBean.isMensajesEnCola()) {
            usuarioSeleccionado.setActivo(Boolean.TRUE);
            if (usuarioSeleccionado.getId() == null) {
                usuarioService.guardarUsuario(usuarioSeleccionado);
                Autenticacion aut = new Autenticacion();
                aut.setCodigo(login);
                aut.setPassword(password);
                aut.setUsuario(usuarioSeleccionado);
                usuarioService.guardarAutenticacion(aut);
            } else {
                usuarioService.actualizarUsuario(usuarioSeleccionado);
                Autenticacion aut = usuarioSeleccionado.getAutenticacion();
                aut.setCodigo(login);
                aut.setPassword(password);
                aut.setUsuario(usuarioSeleccionado);
                usuarioService.actualizarAutenticacion(aut);
            }

            UtilBean.redirect("usuario/registro/usuarios.jsf");
            buscarUsuarios();
        }
    }

    public void atras() {
        UtilBean.redirect("usuario/registro/usuarios.jsf");
    }

    public String verUsuario(final Usuario usuario) {
        usuarioSeleccionado = usuario;
        login = usuario.getAutenticacion().getCodigo();
        password = null;
        passwordNuevamente = null;
        return "usuario";
    }

    public String nuevoUsuario() {
        usuarioSeleccionado = new Usuario();
        login = null;
        password = null;
        passwordNuevamente = null;
        return "usuario";
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }

    public String getDocumentoBusqueda() {
        return documentoBusqueda;
    }

    public void setDocumentoBusqueda(String documentoBusqueda) {
        this.documentoBusqueda = documentoBusqueda;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNuevamente() {
        return passwordNuevamente;
    }

    public void setPasswordNuevamente(String passwordNuevamente) {
        this.passwordNuevamente = passwordNuevamente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
