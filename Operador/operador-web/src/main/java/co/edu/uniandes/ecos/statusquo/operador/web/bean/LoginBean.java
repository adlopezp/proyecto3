/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import co.edu.uniandes.ecos.statusquo.operador.ejb.UsuarioEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    @EJB
    UsuarioEJB usuarioService;

    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginAction() {
        if (usuario != null && !usuario.isEmpty() && password != null && !password.isEmpty()) {
            Usuario usuarioEnt = usuarioService.auntenticar(usuario, password);
            if (usuarioEnt == null) {
                UtilBean.printMensajeError("Error de Usuario/Contraseña");
            } else {
                limpiarSesion();
                HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                sesion.setAttribute("Usuario", usuarioEnt);
                UtilBean.redirect("portal.jsf");
            }

        } else {
            UtilBean.printMensajeError("Error de Usuario/Contraseña");
        }

    }

    public void logoutAction() {
        limpiarSesion();
        UtilBean.redirectLogin();
    }

    public static void limpiarSesion() {
        HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sesion.removeAttribute("sesionBean");
        sesion.removeAttribute("documentView");
        sesion.removeAttribute("Usuario");
    }

    public boolean isRenderPortal() {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final HttpServletRequest request = (HttpServletRequest) context.getRequest();
        if (usuario == null) {
            usuario = "usuario";
        }
        if (request.getRequestURI().contains("portal")) {
            UtilBean.redirect(usuario.equalsIgnoreCase("usuario") ? "usuario/buzon/buzon-electronico.jsf" : usuario.equalsIgnoreCase("entidad") ? "entidad/tramites-basicos.jsf" : usuario.equalsIgnoreCase("centralizador") ? "centralizador/entidades.jsf" : "usuario/buzon/buzon-electronico.jsf");
            return false;
        }
        return true;
    }

}
