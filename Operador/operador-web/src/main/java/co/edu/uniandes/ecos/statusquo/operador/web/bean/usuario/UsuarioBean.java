/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean.usuario;

import co.edu.uniandes.ecos.statusquo.operador.ejb.UsuarioEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Autenticacion;
import co.edu.uniandes.ecos.statusquo.operador.entity.TipoUsuario;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.util.Util;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

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
    private String documento;
    private String login;
    private String password;
    private String passwordNuevamente;
    private String archivo;
    //Tipos
    private List<TipoUsuario> tiposUsuarios;
    private Long tipoUsuario;
    //Modificacion
    private boolean editarPassword;

    @PostConstruct
    public void init() {
        tiposUsuarios = usuarioService.getTiposUsuario();
        usuarioSeleccionado = new Usuario();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void buscarUsuarios() {
        usuarios = usuarioService.buscarUsuarios(nombreBusqueda, documentoBusqueda);
    }

    public void guardarUsuario() {
        if (tipoUsuario == null) {
            UtilBean.printMensajeErrorSimple("Tipo Usuario Obligatorio");
        }
        if (usuarioSeleccionado.getTipoDocumento() == null) {
            UtilBean.printMensajeErrorSimple("Tipo Documento Obligatorio");
        }
        if (Util.isEmpty(documento)) {
            UtilBean.printMensajeErrorSimple("Documento Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getNombre1())) {
            UtilBean.printMensajeErrorSimple("Primer Nombre Obligatorio");
        }
        if (Util.isEmpty(usuarioSeleccionado.getApellido1())) {
            UtilBean.printMensajeErrorSimple("Primer Apellido Obligatorio");
        }
        if (editarPassword && (Util.isEmpty(password) || Util.isEmpty(passwordNuevamente))) {
            UtilBean.printMensajeErrorSimple("Contraseña Obligatoria");
        }
        if (editarPassword && (!Util.isEmpty(password) && !Util.isEmpty(passwordNuevamente) && !password.equals(passwordNuevamente))) {
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
            Usuario usuariosAntiguo = usuarioService.obtenerUsuario(documento);
            if (usuariosAntiguo != null && !usuariosAntiguo.getId().equals(usuarioSeleccionado.getId())) {
                UtilBean.printMensajeErrorSimple("Ya existe un usuario con este número de identificación");
            }
            Autenticacion autenticacionAntigua = usuarioService.obtenerAutenticacion(login);
            Long idAutenticacionActual = usuarioSeleccionado.getAutenticacion() == null ? null : usuarioSeleccionado.getAutenticacion().getId();
            if (autenticacionAntigua != null && !autenticacionAntigua.getId().equals(idAutenticacionActual)) {
                UtilBean.printMensajeErrorSimple("Ya existe un usuario con este nombre de usuario");
            }
        }

        if (!UtilBean.isMensajesEnCola()) {
            usuarioSeleccionado.setActivo(Boolean.TRUE);
            usuarioSeleccionado.setDocumento(documento);
            for (TipoUsuario tipo : tiposUsuarios) {
                if (tipo.getId().equals(tipoUsuario)) {
                    usuarioSeleccionado.setTipo(tipo);
                    break;
                }
            }
            if (usuarioSeleccionado.getId() == null) {
                usuarioService.guardarUsuario(usuarioSeleccionado);
                Autenticacion aut = new Autenticacion();
                aut.setCodigo(login);
                aut.setPassword(password);
                aut.setUsuario(usuarioSeleccionado);
                usuarioSeleccionado.setAutenticacion(aut);
                usuarioService.guardarAutenticacion(aut);
                usuarioService.actualizarUsuario(usuarioSeleccionado);
            } else {
                usuarioService.actualizarUsuario(usuarioSeleccionado);
                Autenticacion aut = usuarioSeleccionado.getAutenticacion();
                aut.setCodigo(login);
                if (editarPassword) {
                    aut.setPassword(password);
                }
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
        tipoUsuario = usuario.getTipo().getId();
        documento = usuario.getDocumento();
        password = null;
        passwordNuevamente = null;
        editarPassword = false;
        return "usuario";
    }

    public String nuevoUsuario() {
        usuarioSeleccionado = new Usuario();
        login = null;
        documento = null;
        tipoUsuario = null;
        password = null;
        passwordNuevamente = null;
        editarPassword = true;
        return "usuario";
    }

    public List<SelectItem> getTiposUsuariosItems() {
        List<SelectItem> items = new ArrayList<>();
        if (tiposUsuarios != null) {
            for (TipoUsuario tipo : tiposUsuarios) {
                items.add(new SelectItem(tipo.getId(), tipo.getNombre()));
            }
        }
        return items;
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

    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isEditarPassword() {
        return editarPassword;
    }

    public void setEditarPassword(boolean editarPassword) {
        this.editarPassword = editarPassword;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
