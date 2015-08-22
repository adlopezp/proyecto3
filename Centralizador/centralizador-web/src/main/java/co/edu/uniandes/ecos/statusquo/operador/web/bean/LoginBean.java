/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import co.edu.uniandes.ecos.statusquo.operador.web.utils.HelperMethods;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alvaro
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String password;
    private String seleccion;
    private String theme = "start";
    private String template = "master-page";
    private List<String> usuarios = new ArrayList<>();
    private Date logonDate;

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
        if (usuario != null && getUsuarios().contains(usuario.toLowerCase())) {
            setTheme(usuario.equalsIgnoreCase("usuario") ? "start" : usuario.equalsIgnoreCase("entidad") ? "hot-sneaks" : usuario.equalsIgnoreCase("centralizador") ? "pepper-grinder" : "start");
            setTemplate(usuario.equalsIgnoreCase("usuario") ? "master-page" : usuario.equalsIgnoreCase("entidad") ? "master-page2" : usuario.equalsIgnoreCase("centralizador") ? "master-page3" : "master-page");
            UtilBean.redirect("portal.jsf");
        } else {
            HelperMethods.showMessageGrowl("Alerta", "Error de usuario y Contraseña");
        }

    }

    public void logoutAction() {
        setTheme("start");
        UtilBean.redirectLogin();
    }

    public String getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

    public Date getLogonDate() {
        return logonDate;
    }

    public void setLogonDate(Date logonDate) {
        this.logonDate = new Date();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getUsuarios() {
        this.usuarios.add("usuario");
        this.usuarios.add("centralizador");
        this.usuarios.add("entidad");
        return usuarios;
    }

    public void setUsuarios(List<String> usuarios) {
        this.usuarios = usuarios;
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
